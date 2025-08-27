package com.example.trainapp.application.service;

import com.example.trainapp.application.port.in.ManageTrainUseCase;
import com.example.trainapp.application.port.in.QueryTrainUseCase;
import com.example.trainapp.application.port.out.TrainRepositoryPort;
import com.example.trainapp.domain.model.Train;
import com.example.trainapp.domain.model.WagonType;
import com.example.trainapp.domain.service.TrainDomainService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implements inbound ports, coordinates domain service and persistence adapter.
 */
@Service
@Transactional
public class TrainApplicationService implements ManageTrainUseCase, QueryTrainUseCase {

    private final TrainDomainService service;
    private final TrainRepositoryPort repository;

    public TrainApplicationService(TrainRepositoryPort repo) {
        this.service = new TrainDomainService();
        this.repository = repo;
    }

    @Override public Train addLeft(WagonType type) {
        Train train = repository.load();
        service.addLeft(train, type);
        repository.save(train);
        return train;
    }

    @Override public Train addRight(WagonType type) {
        Train train = repository.load();
        service.addRight(train, type);
        repository.save(train);
        return train;
    }

    @Override public Train removeLeft() {
        Train train = repository.load();
        service.removeLeft(train);
        repository.save(train);
        return train;
    }

    @Override public Train removeRight() {
        Train train = repository.load();
        service.removeRight(train);
        repository.save(train);
        return train;
    }

    @Override public Train replaceAt(int index, WagonType type) {
        Train train = repository.load();
        service.replaceAt(train, index, type);
        repository.save(train);
        return train;
    }

    @Override public Train reset(String compact) {
        Train train = Train.fromCompactString(compact == null ? "" : compact);
        repository.save(train);
        return train;
    }

    @Override public Train sortOrThrow() {
        Train train = repository.load();
        Train sorted = service.trySort(train).orElseThrow(
                () -> new IllegalStateException("No valid sequence for given wagons possible.")
        );
        repository.save(sorted);
        return sorted;
    }

    @Override @Transactional(readOnly = true) public Train get() {
        return repository.load();
    }

    @Override @Transactional(readOnly = true) public boolean validate() {
        return service.validate(repository.load());
    }

}