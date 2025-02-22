package com.example.spribe_test_task.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Getter
@Setter
@Entity
@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor
public class UnitProperties {

    @Id
    @Column(name = "unit_id", nullable = false)
    private Long unitId;

    @NonNull
    private Integer floor;

    @NonNull
    private AccommodationType type;

    @NonNull
    private Integer roomsCount;
}
