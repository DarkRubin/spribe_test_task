package com.example.spribe_test_task.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "unit_properties")
@NoArgsConstructor
public class UnitProperties {
    @Id
    @Column(name = "unit_id", nullable = false)
    private UUID id;

    @MapsId
    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "unit_id", nullable = false, referencedColumnName = "id")
    private Unit unit;

    @NotNull
    @Column(name = "floor", nullable = false)
    private Integer floor;

    @NotNull
    @Column(name = "rooms_count", nullable = false)
    private Integer roomsCount;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", columnDefinition = "accommodation_type",  nullable = false)
    private AccommodationType type;

    public UnitProperties(Integer floor, Integer roomsCount, AccommodationType type) {
        this.floor = floor;
        this.roomsCount = roomsCount;
        this.type = type;
    }
}