package com.example.spribe_test_task.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class PaymentDto {
    private UUID eventId;
    private Long userId;
}