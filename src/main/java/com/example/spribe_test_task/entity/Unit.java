package com.example.spribe_test_task.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Getter @Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Unit {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(nullable = false)
    private UUID id;

    private String description;

    @OneToOne(cascade = CascadeType.PERSIST)
    private UnitProperties properties;

    private BigDecimal cost;

    @OneToMany
    private List<Event> events;


    public Unit(String description, UnitProperties properties, BigDecimal cost) {
        this.description = description;
        this.properties = properties;
        this.cost = cost;
    }
}
