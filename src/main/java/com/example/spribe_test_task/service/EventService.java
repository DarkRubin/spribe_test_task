package com.example.spribe_test_task.service;

import com.example.spribe_test_task.dto.EventDto;
import com.example.spribe_test_task.entity.Event;
import com.example.spribe_test_task.entity.Unit;
import com.example.spribe_test_task.entity.User;
import com.example.spribe_test_task.excption.EventCreationException;
import com.example.spribe_test_task.repository.EventRepository;
import com.example.spribe_test_task.repository.UnitRepository;
import com.example.spribe_test_task.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EventService {

    private final EventRepository eventRepository;
    private final UserRepository userRepository;
    private final UnitRepository unitRepository;


    public List<Event> findAllByUnitId(Pageable pageable, UUID unitId) {
        return eventRepository.findAllByUnit_Id(unitId, pageable).getContent();
    }

    @Transactional
    public Event bookUnitWithId(UUID unitId, EventDto bookingEventDto) {
        if (eventRepository.existsByUnit_IdAndStartTimeBeforeAndEndTimeAfter(unitId,
                bookingEventDto.getStartTime(), bookingEventDto.getEndTime())) {
            throw new EventCreationException("Unit not available in this diapason");
        }

        User user = userRepository.findById(bookingEventDto.getUserId())
                .orElseThrow(EntityNotFoundException::new);
        Unit unit = unitRepository.findById(unitId)
                .orElseThrow(EntityNotFoundException::new);

        Event event = new Event(bookingEventDto.getStartTime(),
                bookingEventDto.getEndTime(),
                user,
                unit);

        return eventRepository.save(event);
    }

    public void cancelBooking(UUID unitId, Long userId) {
        eventRepository.deleteByUnit_IdAndUser_Id(unitId, userId);
    }
}
