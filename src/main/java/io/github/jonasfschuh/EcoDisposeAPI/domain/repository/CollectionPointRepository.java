package io.github.jonasfschuh.EcoDisposeAPI.domain.repository;

import io.github.jonasfschuh.EcoDisposeAPI.domain.model.CollectionPoint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CollectionPointRepository extends JpaRepository<CollectionPoint, Long> {

}
