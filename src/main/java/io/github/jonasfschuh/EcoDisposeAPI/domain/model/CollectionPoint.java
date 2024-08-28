package io.github.jonasfschuh.EcoDisposeAPI.domain.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity(name = "tb_collectionPoint")
@Getter
@Setter
public class CollectionPoint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToOne(cascade = CascadeType.ALL)
    private ReceivingEntity  receivingEntity ;

    @OneToOne(cascade = CascadeType.ALL)
    private Address address;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Material> material;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Notification> notification;

}
