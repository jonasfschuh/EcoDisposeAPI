package io.github.jonasfschuh.EcoDisposeAPI.service.impl;

import io.github.jonasfschuh.EcoDisposeAPI.domain.model.CollectionPoint;
import io.github.jonasfschuh.EcoDisposeAPI.domain.repository.CollectionPointRepository;
import io.github.jonasfschuh.EcoDisposeAPI.service.CollectionPointService;
import io.github.jonasfschuh.EcoDisposeAPI.service.exception.BusinessException;
import io.github.jonasfschuh.EcoDisposeAPI.service.exception.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.Optional.ofNullable;

@Service
public class CollectionPointServiceImpl implements CollectionPointService {

    private static final Long UNCHANGEABLE_USER_ID = 1L;

    private final CollectionPointRepository collectionPointRepository;

    public CollectionPointServiceImpl(CollectionPointRepository collectionPointRepository) {
        this.collectionPointRepository = collectionPointRepository;
    }

    @Transactional(readOnly = true)
    public List<CollectionPoint> findAll() {
        return collectionPointRepository.findAll();
    }

    @Transactional(readOnly = true)
    public CollectionPoint findById(Long id) {
        return collectionPointRepository.findById(id).orElseThrow(NotFoundException::new);
    }

    @Transactional
    public CollectionPoint create(CollectionPoint collectionPointToCreate) {
        ofNullable(collectionPointToCreate).orElseThrow(() -> new BusinessException("collection point to create must not be null"));
        ofNullable(collectionPointToCreate.getReceivingEntity()).orElseThrow(() -> new BusinessException("receiving entity must not be null."));
        ofNullable(collectionPointToCreate.getAddress()).orElseThrow(() -> new BusinessException("address must not be null."));

        validateChangeableId(collectionPointToCreate.getId(), "created");
        return collectionPointRepository.save(collectionPointToCreate);
    }

    @Transactional
    public CollectionPoint update(Long id, CollectionPoint collectionPointToUpdate) {
        this.validateChangeableId(id, "updated");
        CollectionPoint dbCollectionPoint = this.findById(id);
        if (!dbCollectionPoint.getId().equals(collectionPointToUpdate.getId())) {
            throw new BusinessException("Update IDs must be the same.");
        }
        dbCollectionPoint.setName(collectionPointToUpdate.getName());
        dbCollectionPoint.setReceivingEntity(collectionPointToUpdate.getReceivingEntity());
        dbCollectionPoint.setAddress(collectionPointToUpdate.getAddress());
        dbCollectionPoint.setMaterial(collectionPointToUpdate.getMaterial());
        dbCollectionPoint.setNotification(collectionPointToUpdate.getNotification());

        return collectionPointRepository.save(dbCollectionPoint);
    }

    @Transactional
    public void delete(Long id) {
        validateChangeableId(id, "deleted");
        CollectionPoint dbCollectionPoint = this.findById(id);
        collectionPointRepository.delete(dbCollectionPoint);
    }

    private void validateChangeableId(Long id, String operation) {
        if (UNCHANGEABLE_USER_ID.equals(id)) {
            throw new BusinessException("Collection Point with ID %d can not be %s.".formatted(UNCHANGEABLE_USER_ID, operation));
        }
    }
}
