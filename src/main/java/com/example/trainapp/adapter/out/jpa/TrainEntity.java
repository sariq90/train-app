package com.example.trainapp.adapter.out.jpa;

import jakarta.persistence.*;

/**
 * Single-row table representation of the train.
 */
@Entity
@Table(name = "train")
public class TrainEntity {
    @Id
    private Long id = 1L;

    @Column(name = "wagons", nullable = false, length = 4096)
    private String wagons;

    public TrainEntity() {}

    public TrainEntity(Long id, String wagons) {
        this.id = id;
        this.wagons = wagons;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWagons() {
        return wagons;
    }

    public void setWagons(String wagons) {
        this.wagons = wagons;
    }

}