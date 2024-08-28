package io.github.jonasfschuh.EcoDisposeAPI.controller.dto;

import io.github.jonasfschuh.EcoDisposeAPI.domain.model.Material;

public record MaterialDto(Long id, String alias, String description) {

    public MaterialDto(Material model) {
        this(model.getId(), model.getAlias(), model.getDescription());
    }

    public Material toModel() {
        Material model = new Material();
        model.setId(this.id);
        model.setAlias(this.alias);
        model.setDescription(this.description);
        return model;
    }
}
