package com.example.spribe_test_task.controller;

import com.example.spribe_test_task.dto.EventDto;
import com.example.spribe_test_task.entity.Event;
import com.example.spribe_test_task.service.EventService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/v1/units/{id}/events")
@RequiredArgsConstructor
@Tag(name = "Event Controller", description = """
        Controller to manage Event entity (abstraction over booking)
         provide create, search and delete operation""")
public class EventController {

    public final EventService eventService;


    @GetMapping
    @Operation(
            summary = "Get all events for this unit with pagination",
            description = """
                    Retrieve a paginated list of events of provided unit,
                     with specified the page number, size, and sorting""",
            tags = { "Event" }
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved units"),
            @ApiResponse(responseCode = "400", description = "Bad request due to invalid pagination parameters or unit ID")
    })
    public ResponseEntity<List<Event>> findAllEvents(
            @Parameter(description = "Page number, default is 0")
            @RequestParam(defaultValue = "0") int page,
            @Parameter(description = "Page size, default is 20")
            @RequestParam(defaultValue = "20") int size,
            @Parameter(description = "Sorting by field (available: id, description, price), default id")
            @RequestParam(defaultValue = "id") String sort,
            @Parameter(description = "ID of unit with events")
            @PathVariable String id) {

        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
        UUID unitId = UUID.fromString(id);
        return ResponseEntity.ok(eventService.findAllByUnitId(pageable, unitId));
    }

    @PostMapping
    @Operation(
            summary = "Create new booking event",
            description = "Create new event with provided date period and user",
            tags = { "Event" }
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Event created successful"),
            @ApiResponse(responseCode = "400", description = "Bad request due invalid event data body or unit ID"),
            @ApiResponse(responseCode = "401", description = "Conflict with existing event")
    })
    public ResponseEntity<Event> createBookEvent(
            @Parameter(description = "ID of unit with events")
            @PathVariable String id,
            @Parameter(description = "Information about new event (startDate, endDate, userId)")
            @RequestBody EventDto eventDto) {

        if (eventDto.getStartTime().isAfter(eventDto.getEndTime())) {
            throw new IllegalArgumentException("Requested start date is after end date!");
        }
        UUID unitId = UUID.fromString(id);
        Event event = eventService.bookUnitWithId(unitId, eventDto);
        return ResponseEntity.created(URI.create(event.getId().toString())).body(event);
    }

    @DeleteMapping
    @Operation(
            summary = "Cancel booking event",
            description = "Remove event with provided unit and user ID",
            tags = { "Event" }
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Event deleted successfully, or event doesn't exist"),
            @ApiResponse(responseCode = "400", description = "Bad request due invalid unit or user ID"),
    })
    public ResponseEntity<Void> delete(
            @Parameter(description = "ID of unit with event to cancel")
            @PathVariable String id,
            @Parameter(description = "ID of user that create event")
            @RequestParam Long userId) {
        UUID unitId = UUID.fromString(id);
        eventService.cancelBooking(unitId, userId);
        return ResponseEntity.ok().build();
    }
}
