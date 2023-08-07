package com.example.unit_test.repository;

import com.example.unit_test.model.GameStep;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameStepRepository extends JpaRepository<GameStep,Long> {
}
