package com.example.spribe_test_task.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.Instant;

@Data
public class EventDto {

    @NotNull
    private Instant startTime;
    @NotNull
    private Instant endTime;
    @NotNull
    private Long userId;
}
