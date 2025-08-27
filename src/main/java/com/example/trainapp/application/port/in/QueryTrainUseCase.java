package com.example.trainapp.application.port.in;

import com.example.trainapp.domain.model.Train;

public interface QueryTrainUseCase {
    Train get();
    boolean validate();
}