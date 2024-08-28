package io.github.jonasfschuh.EcoDisposeAPI.controller;

import io.github.jonasfschuh.EcoDisposeAPI.controller.dto.CollectionPointDto;
import io.github.jonasfschuh.EcoDisposeAPI.service.CollectionPointService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping("/collectionpoint")
@Tag(name = "Collection Point Controller", description = "EcoDispose API for find collection points " +
        "for environmentally sensitive materials. Be ecologically sustainable!.")
public record CollectionPointController(CollectionPointService collectionPointService) {

    @GetMapping
    @Operation(summary = "Get all collection points", description = "Retrieve a list of all registered collection points")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operation successful")
    })
    public ResponseEntity<List<CollectionPointDto>> findAll() {
        var collectionPoints = collectionPointService.findAll();
        var collectionPointsDto = collectionPoints.stream().map(CollectionPointDto::new).collect(Collectors.toList());
        return ResponseEntity.ok(collectionPointsDto);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a collection point by ID", description = "Retrieve a specific collection point on its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operation sucessful"),
            @ApiResponse(responseCode = "404", description = "Collection point not found")
    })
    public ResponseEntity<CollectionPointDto> findById(@PathVariable Long id) {
        var collectionPoint = collectionPointService.findById(id);
        return ResponseEntity.ok(new CollectionPointDto(collectionPoint));
    }

    @PostMapping
    @Operation(summary = "Create a new collection point", description = "Create a new collection point and return the create collection point´s data")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Collection point created successfully"),
            @ApiResponse(responseCode = "422", description = "Invalid user data provided")
    })
    public ResponseEntity<CollectionPointDto> create(@RequestBody CollectionPointDto collectionPointDto) {
        var collectionPoint = collectionPointService.create(collectionPointDto.toModel());
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(collectionPoint.getId())
                .toUri();
        return ResponseEntity.created(location).body(new CollectionPointDto(collectionPoint));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a collection point", description = "Update the data of an existing collection point based on its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Collection point updated successfully"),
            @ApiResponse(responseCode = "404", description = "Collection point not found"),
            @ApiResponse(responseCode = "422", description = "Invalid user data provided")
    })
    public ResponseEntity<CollectionPointDto> update(@PathVariable Long id, @RequestBody CollectionPointDto collectionPointDto) {
        var collectionPoint = collectionPointService.update(id, collectionPointDto.toModel());
        return ResponseEntity.ok(new CollectionPointDto(collectionPoint));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a collection point", description = "Delete an existing collection point based on its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Collection point deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Collection point not found")
    })

    public ResponseEntity<Void> delete(@PathVariable Long id) {
        collectionPointService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
