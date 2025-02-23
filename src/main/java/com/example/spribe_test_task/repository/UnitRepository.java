package com.example.spribe_test_task.repository;

import com.example.spribe_test_task.entity.AccommodationType;
import com.example.spribe_test_task.entity.Unit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.time.Instant;

public interface UnitRepository extends JpaRepository<Unit, Long> {

    Page<Unit> findAllByPriceBetween(Pageable pageable, BigDecimal costAfter, BigDecimal costBefore);

    @Query("""
            select count(u) from Unit u right join Event events on events.unit = u
            where events.endTime < ?1 or events.startTime > ?2""")
    Long countAllAvailable(Instant startTime, Instant endTime);

    Page<Unit> findByProperties_Type(Pageable pageable, AccommodationType propertiesType);

    @Query("""
            select u from Unit u left join Event events on events.unit = u
            where u.properties.type = ?1 and events.endTime < ?2 or events.startTime > ?3""")
    Page<Unit> findAllAvailableByType(Pageable pageable, AccommodationType type, Instant startTime, Instant endTime);

    Page<Unit> findByProperties_Floor(Pageable pageable, Integer floor);

    @Query("""
            select u from Unit u inner join Event events on events.unit = u
            where events.endTime < ?1 or events.startTime > ?2""")
    Page<Unit> findAllAvailable(Pageable pageable, Instant startTime, Instant endTime);

    @Query("""
            select u from Unit u left join Event events on events.unit = u
            where u.properties.floor = ?1 and events.endTime < ?2 or events.startTime > ?3""")
    Page<Unit> findAllAvailableByFloor(Pageable pageable, Integer floor, Instant start, Instant end);

    Page<Unit> findByProperties_RoomsCount(Pageable pageable, Integer roomsCount);

    @Query("""
            select u from Unit u left join Event events on events.unit = u
            where u.properties.roomsCount = ?1 and events.endTime < ?2 or events.startTime > ?3""")
    Page<Unit> findAllAvailableByRoomsCount(Pageable pageable, Integer roomsCount, Instant start, Instant end);
}