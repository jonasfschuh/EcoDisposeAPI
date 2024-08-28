package io.github.jonasfschuh.EcoDisposeAPI.controller.dto;

import io.github.jonasfschuh.EcoDisposeAPI.domain.model.ReceivingEntity;

public record ReceivingEntityDto(Long id, String name) {

    public ReceivingEntityDto(ReceivingEntity model) {
        this(model.getId(), model.getName());
    }

    public ReceivingEntity toModel() {
        ReceivingEntity model = new ReceivingEntity();
        model.setId(this.id);
        model.setName(this.name);
        return model;
    }
}
