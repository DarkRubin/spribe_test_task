package com.example.spribe_test_task.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "unit_properties")
public class UnitProperties {
    @Id
    @Column(name = "unit_id", nullable = false)
    private UUID id;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "unit_id", nullable = false)
    private Unit unit;

    @NotNull
    @Column(name = "floor", nullable = false)
    private Integer floor;

    @NotNull
    @Column(name = "rooms_count", nullable = false)
    private Integer roomsCount;


    @Column(name = "type", columnDefinition = "accommodation_type",  nullable = false)
    private AccommodationType type;

}