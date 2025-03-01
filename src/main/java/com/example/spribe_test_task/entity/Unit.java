package com.example.spribe_test_task.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "units")
@NoArgsConstructor
public class Unit {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
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

    @OneToMany(targetEntity = Event.class)
    @JsonIgnore
    private List<Event> events = new ArrayList<>();

    public Unit(String description, BigDecimal price) {
        this.description = description;
        this.price = price;
    }
}