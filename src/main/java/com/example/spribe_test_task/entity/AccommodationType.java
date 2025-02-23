package com.example.spribe_test_task.entity;

public enum AccommodationType {
    HOME,
    FLAT,
    APARTMENTS;

    public static AccommodationType fromString(String value) {
        return valueOf(value.trim().toUpperCase());
    }
}
