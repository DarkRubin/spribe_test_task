package com.example.spribe_test_task.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "payments", uniqueConstraints = {
        @UniqueConstraint(name = "payments_pk_2", columnNames = {"event_id"})
})
@NoArgsConstructor
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private UUID id;

    @NotNull
    @OneToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "event_id", nullable = false)
    private Event event;

    @JsonIgnore
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Payment(Event event, User user) {
        this.event = event;
        this.user = user;
    }
}