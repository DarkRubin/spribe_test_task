package com.example.spribe_test_task.dto;

import com.example.spribe_test_task.entity.AccommodationType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UnitPropertiesDto {
    private Integer floor;
    private Integer roomsCount;
    private AccommodationType type;
}
