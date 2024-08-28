package io.github.jonasfschuh.EcoDisposeAPI.controller.dto;

import io.github.jonasfschuh.EcoDisposeAPI.domain.model.Address;

public record AddressDto(Long id, String street, String neighborhood, String city) {

    public AddressDto(Address model) {
        this(model.getId(), model.getStreet(), model.getNeighborhood(), model.getCity());
    }

    public Address toModel() {
        Address model = new Address();
        model.setId(this.id);
        model.setStreet(this.street);
        model.setNeighborhood(this.neighborhood);
        model.setCity(this.city);
        return model;
    }
}
