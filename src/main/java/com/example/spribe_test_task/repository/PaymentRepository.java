package com.example.spribe_test_task.repository;

import com.example.spribe_test_task.entity.Payment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PaymentRepository extends JpaRepository<Payment, UUID> {
    Page<Payment> findAllByUser_Id(Pageable pageable, Long userId);
}
