package com.example.trainapp.application.port.out;

import com.example.trainapp.domain.model.Train;

public interface TrainRepositoryPort {
    Train load();
    void save(Train train);
}