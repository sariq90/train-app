package com.example.trainapp.adapter.out.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TrainJpaRepository extends JpaRepository<TrainEntity, Long> {

}