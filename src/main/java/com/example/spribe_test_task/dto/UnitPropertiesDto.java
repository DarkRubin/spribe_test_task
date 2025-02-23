package com.example.spribe_test_task.dto;

import com.example.spribe_test_task.entity.AccommodationType;
import lombok.Data;

@Data
public class UnitPropertiesDto {
    private Integer floor;
    private AccommodationType type;
    private Integer roomsCount;
}
