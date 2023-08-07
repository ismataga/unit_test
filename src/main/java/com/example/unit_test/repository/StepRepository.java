package com.example.unit_test.repository;

import com.example.unit_test.model.Step;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StepRepository extends JpaRepository<Step,Long> {
}
