package io.github.jonasfschuh.EcoDisposeAPI.controller.dto;

import io.github.jonasfschuh.EcoDisposeAPI.domain.model.CollectionPoint;

import java.util.List;

import static java.util.Collections.emptyList;
import static java.util.Optional.ofNullable;
import static java.util.stream.Collectors.toList;

public record CollectionPointDto(
        Long id,
        String name,
        ReceivingEntityDto receivingEntity,
        AddressDto address,
        List<MaterialDto> material,
        List<NotificationDto> notification) {

    public CollectionPointDto(CollectionPoint model) {
        this(
                model.getId(),
                model.getName(),
                ofNullable(model.getReceivingEntity()).map(ReceivingEntityDto::new).orElse(null),
                ofNullable(model.getAddress()).map(AddressDto::new).orElse(null),
                ofNullable(model.getMaterial()).orElse(emptyList()).stream().map(MaterialDto::new).collect(toList()),
                ofNullable(model.getNotification()).orElse(emptyList()).stream().map(NotificationDto::new).collect(toList())
        );
    }

    public CollectionPoint toModel() {
        CollectionPoint model = new CollectionPoint();
        model.setId(this.id);
        model.setName(this.name);
        model.setReceivingEntity(ofNullable(this.receivingEntity).map(ReceivingEntityDto::toModel).orElse(null));
        model.setAddress(ofNullable(this.address).map(AddressDto::toModel).orElse(null));
        model.setMaterial(ofNullable(this.material).orElse(emptyList()).stream().map(MaterialDto::toModel).collect(toList()));
        model.setNotification(ofNullable(this.notification).orElse(emptyList()).stream().map(NotificationDto::toModel).collect(toList()));
        return model;
    }

}
