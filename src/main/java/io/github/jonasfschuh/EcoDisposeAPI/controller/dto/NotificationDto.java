package io.github.jonasfschuh.EcoDisposeAPI.controller.dto;

import io.github.jonasfschuh.EcoDisposeAPI.domain.model.Notification;

public record NotificationDto(Long id, String description, Boolean viewed) {

    public NotificationDto(Notification model) {
        this(model.getId(), model.getDescription(), model.getViewed());
    }

    public Notification toModel() {
        Notification model = new Notification();
        model.setId(this.id);
        model.setDescription(this.description);
        model.setViewed(this.viewed);
        return model;
    }
}
