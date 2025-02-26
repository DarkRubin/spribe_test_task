package com.example.spribe_test_task.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UnitDto {

    private String description;
    private UnitPropertiesDto properties;
    private BigDecimal cost;
}