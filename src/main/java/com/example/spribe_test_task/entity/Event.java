package com.example.spribe_test_task.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Generated;

import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "events")
@NoArgsConstructor
@AllArgsConstructor
public class Event {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotNull
    @Column(name = "start_time", nullable = false)
    private Instant startTime;

    @NotNull
    @Column(name = "end_time", nullable = false)
    private Instant endTime;

    @Generated
    @Column(name = "creation_time", insertable = false, updatable = false)
    private Instant creationTime;

    @JsonIgnore
    @JoinColumn(referencedColumnName = "id")
    @OneToOne(fetch = FetchType.LAZY)
    private Payment payment;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @NotNull
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "unit_id", nullable = false)
    private Unit unit;

    public Event(@NotNull Instant startTime, @NotNull Instant endTime, User user, Unit unit) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.user = user;
        this.unit = unit;
    }

    public Event(UUID id) {
        this.id = id;
    }
}