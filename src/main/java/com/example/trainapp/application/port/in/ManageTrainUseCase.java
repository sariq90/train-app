package com.example.trainapp.application.port.in;

import com.example.trainapp.domain.model.Train;
import com.example.trainapp.domain.model.WagonType;

public interface ManageTrainUseCase {
    Train addLeft(WagonType type);
    Train addRight(WagonType type);
    Train removeLeft();
    Train removeRight();
    Train replaceAt(int index, WagonType type);
    Train reset(String compact);
    Train sortOrThrow();
}