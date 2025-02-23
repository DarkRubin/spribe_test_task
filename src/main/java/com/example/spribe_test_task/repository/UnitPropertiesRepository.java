package com.example.spribe_test_task.repository;

import com.example.spribe_test_task.entity.UnitProperties;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UnitPropertiesRepository extends JpaRepository<UnitProperties, UUID> {
}