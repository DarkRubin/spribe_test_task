package com.example.spribe_test_task.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@Entity
@Table(name = "users")
@NoArgsConstructor
@RequiredArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Size(max = 20)
    @NonNull
    @Column(name = "username", nullable = false, length = 20)
    private String username;

    @Size(max = 70)
    @NonNull
    @Column(name = "password", nullable = false, length = 70)
    private String password;

}