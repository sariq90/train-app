package com.example.trainapp.adapter.out.jpa;

import com.example.trainapp.application.port.out.TrainRepositoryPort;
import com.example.trainapp.domain.model.Train;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Converts between entity <-> aggregate.
 */
@Component
public class TrainPersistenceAdapter implements TrainRepositoryPort {

    private final TrainJpaRepository repository;

    public TrainPersistenceAdapter(TrainJpaRepository jpaRepository) {
        this.repository = jpaRepository;
    }

    @Override @Transactional(readOnly = true)
    public Train load() {
        return repository.findById(1L)
                .map(entity -> Train.fromCompactString(entity.getWagons()))
                .orElseGet(() -> {
                    Train train = new Train(List.of());
                    repository.save(new TrainEntity(1L, train.toCompactString()));
                    return train;
                });
    }

    @Override @Transactional
    public void save(Train train) {
        repository.save(new TrainEntity(1L, train.toCompactString()));
    }

}