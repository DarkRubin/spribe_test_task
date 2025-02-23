package com.example.spribe_test_task.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class UnitDto {

    private String description;
    private UnitPropertiesDto properties;
    private BigDecimal cost;
}