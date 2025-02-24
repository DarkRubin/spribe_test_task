package com.example.spribe_test_task.repository;

import com.example.spribe_test_task.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
