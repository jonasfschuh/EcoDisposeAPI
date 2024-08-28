package io.github.jonasfschuh.EcoDisposeAPI.domain.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "tb_receivingEntity")
@Getter
@Setter
public class ReceivingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
}
