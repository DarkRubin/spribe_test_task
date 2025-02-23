package com.example.spribe_test_task.service;

import com.example.spribe_test_task.entity.AccommodationType;
import com.example.spribe_test_task.dto.UnitDto;
import com.example.spribe_test_task.entity.Unit;
import com.example.spribe_test_task.entity.UnitProperties;
import com.example.spribe_test_task.mapper.UnitMapper;
import com.example.spribe_test_task.repository.UnitPropertiesRepository;
import com.example.spribe_test_task.repository.UnitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@RequiredArgsConstructor
@Service
public class UnitService {

    public static final BigDecimal MARKUP = BigDecimal.valueOf(0.15);

    private final UnitRepository unitRepository;
    private final UnitMapper unitMapper;

    private final UnitPropertiesRepository unitPropertiesRepository;

    public List<Unit> findByPriceBetween(Pageable pageable, BigDecimal min, BigDecimal max) {
        return unitRepository.findAllByPriceBetween(pageable, min, max).getContent();
    }

    public List<Unit> findAll(Pageable pageable) {
        return unitRepository.findAll(pageable).getContent();
    }

    public List<Unit> findAllAvailable(Pageable pageable, Instant start, Instant end) {
        return unitRepository.findAllAvailable(pageable, start, end).getContent();
    }

    public Long countAllAvailable(Instant start, Instant end) {
        return unitRepository.countAllAvailable(start, end);
    }

    @Transactional
    public Unit create(UnitDto unitDto) {
        Unit unit = unitMapper.toEntity(unitDto);
        unit.setPrice(unitDto.getCost().add(unitDto.getCost().multiply(MARKUP)));

        unitRepository.save(unit);
        UnitProperties properties = unit.getProperties();
        properties.setUnit(unit);
        unitPropertiesRepository.save(properties);
        return unit;
    }

    public List<Unit> findByAccommodationType(Pageable pageable, AccommodationType type) {
        return unitRepository.findByProperties_Type(pageable, type).getContent();
    }

    public List<Unit> findAllAvailableByAccommodationType(Pageable pageable, AccommodationType type,  Instant start, Instant end) {
        return unitRepository.findAllAvailableByType(pageable, type, start, end).getContent();
    }

    public List<Unit> findByFloor(Pageable pageable, Integer floor) {
        return unitRepository.findByProperties_Floor(pageable, floor).getContent();
    }

    public List<Unit> findAllAvailableByFloor(Pageable pageable, Integer floor, Instant start, Instant end) {
        return unitRepository.findAllAvailableByFloor(pageable, floor, start, end).getContent();
    }

    public List<Unit> findByNumberOfRooms(Pageable pageable, Integer rooms) {
        return unitRepository.findByProperties_RoomsCount(pageable, rooms).getContent();
    }

    public List<Unit> findAllAvailableByCountOfRooms(Pageable pageable, Integer rooms, Instant start, Instant end) {
        return unitRepository.findAllAvailableByRoomsCount(pageable, rooms, start, end).getContent();
    }
}
