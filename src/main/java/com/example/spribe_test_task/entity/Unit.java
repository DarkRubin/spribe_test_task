package com.example.spribe_test_task.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "units")
public class Unit {
    @Id
    @Column(name = "id", nullable = false)
    private UUID id;

    @Size(max = 256)
    @Column(name = "description", length = 256)
    private String description;

    @NotNull
    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @OneToOne
    @PrimaryKeyJoinColumn(referencedColumnName = "unit_id")
    private UnitProperties properties;

    @OneToMany
    private List<Event> events;

}