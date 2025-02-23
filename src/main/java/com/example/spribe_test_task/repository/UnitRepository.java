package com.example.spribe_test_task.repository;

import com.example.spribe_test_task.entity.AccommodationType;
import com.example.spribe_test_task.entity.Unit;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

public interface UnitRepository extends JpaRepository<Unit, Long> {

    Page<Unit> findAllByPriceBetween(Pageable pageable, BigDecimal costAfter, BigDecimal costBefore);

    Long countAllByEvents_StartTimeAfterOrEvents_EndTimeBefore(Instant startTime, Instant endTime);

    Page<Unit> findByProperties_Type(Pageable pageable, AccommodationType propertiesType);

    @Query("""
            select u from Unit u left join u.events events
            where u.properties.type = ?1 and events.startTime > ?2 or events.endTime < ?3""")
    Page<Unit> findAllAvailableByType(Pageable pageable, AccommodationType propertiesType, Instant startTime, Instant endTime);

    Page<Unit> findByProperties_Floor(Pageable pageable, Integer floor);

    Page<Unit> findByEvents_EndTimeBeforeOrEvents_StartTimeAfter(Pageable pageable, Instant eventsEndTimeBefore, Instant eventsStartTimeAfter);

    @Query("""
            select u from Unit u left join u.events events
            where u.properties.floor = ?1 and events.startTime > ?2 or events.endTime < ?3""")
    Page<Unit> findAllAvailableByFloor(Pageable pageable, Integer floor, Instant start, Instant end);

    List<Unit> findByProperties_RoomsCount(Pageable pageable, Integer roomsCount);
}