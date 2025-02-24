package com.example.spribe_test_task.repository;

import com.example.spribe_test_task.entity.Event;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.UUID;

public interface EventRepository extends JpaRepository<Event, UUID> {

    Page<Event> findAllByUnit_Id(UUID unitId, Pageable pageable);

    boolean existsByUnit_IdAndStartTimeBeforeAndEndTimeAfter(UUID unitId, @NotNull Instant startTimeBefore, @NotNull Instant endTimeAfter);

    @Modifying
    @Transactional
    void deleteByUnit_IdAndUser_Id(UUID unitId, Long userId);

    @Modifying
    @Transactional
    @Query("delete Event e where e.payment is null and e.creationTime <= ?1")
    Integer deleteByPaymentDeadlineExpired(Instant paymentDeadline);
}