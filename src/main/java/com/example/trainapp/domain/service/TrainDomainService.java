package com.example.trainapp.domain.service;

import com.example.trainapp.domain.model.Train;
import com.example.trainapp.domain.model.WagonType;

import java.util.Optional;

/**
 * Wrapper for Train logic.
 */
public class TrainDomainService {
    public boolean validate(Train train) {
        return train.isValid();
    }
    public Train addLeft(Train train, WagonType w) {
        train.addLeft(w); return train;
    }
    public Train addRight(Train train, WagonType w) {
        train.addRight(w); return train;
    }
    public Train removeLeft(Train train) {
        train.removeLeft(); return train;
    }
    public Train removeRight(Train train) {
        train.removeRight(); return train;
    }
    public Train replaceAt(Train train, int index, WagonType wagon) {
        train.replaceAt(index, wagon); return train;
    }
    public Optional<Train> trySort(Train train) {
        return train.trySortToValid();
    }

}