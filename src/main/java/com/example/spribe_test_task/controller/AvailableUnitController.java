package com.example.spribe_test_task.controller;

import com.example.spribe_test_task.entity.AccommodationType;
import com.example.spribe_test_task.entity.Unit;
import com.example.spribe_test_task.service.UnitService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/v1/units/available")
@RequiredArgsConstructor
@Tag(name = "Available Unit Controller", description = """
        Controller for manage available Unit entity where events time diapason don't math with your,
         provide search by different properties (like, availability, floor,
         accommodation type or count of rooms) and count operations""")
public class AvailableUnitController {

    private final UnitService unitService;

    @GetMapping("/quantity")
    @Operation(
            summary = "Get quantity of all available units",
            description = "Retrieve a quantity of all available units. Available means no events on provided time range",
            tags = {"Unit"}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Count successfully"),
            @ApiResponse(responseCode = "400", description = "Bad request due to invalid date parameters")
    })
    public Map<String, Long> countAvailable(
            @Parameter(description = "You want to rent from the date:")
            @RequestParam(value = "startTime") Instant start,
            @Parameter(description = "You want rent until the date:")
            @RequestParam("endTime") Instant end) {
        if (start.isAfter(end)) {
            throw new IllegalArgumentException("Requested start date is after end date!");
        }
        return Map.of("quantity", unitService.countAllAvailable(start, end));
    }

    @GetMapping
    @Operation(
            summary = "Get all units with pagination",
            description = "Retrieve a paginated list of units, for provided date range, size, and sorting",
            tags = { "Unit" }
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved units"),
            @ApiResponse(responseCode = "400", description = "Bad request due to invalid pagination or date parameters")
    })
    public List<Unit> findAllAvailable(
            @Parameter(description = "Page number, default is 0")
            @RequestParam(defaultValue = "0") int page,
            @Parameter(description = "Page size, default is 20")
            @RequestParam(defaultValue = "20") int size,
            @Parameter(description = "Sorting by field (available: id, description, price), default id")
            @RequestParam(defaultValue = "id") String sort,
            @Parameter(description = "You want to rent from the date:")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            @RequestParam(value = "startTime") Instant start,
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            @Parameter(description = "You want rent until the date:")
            @RequestParam("endTime") Instant end) {

        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
        if (start.isAfter(end)) {
            throw new IllegalArgumentException("Requested start date is after end date!");
        }
        return unitService.findAllAvailable(pageable, start, end);
    }

    @GetMapping("/type/{accommodationType}")
    @Operation(
            summary = "Get page of all available units of this type",
            description = "Retrieve a paginated list of units, for provided date range and type, size, and sorting"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved units"),
            @ApiResponse(responseCode = "400", description = "Bad request due to invalid pagination, type or date parameters")
    })
    public List<Unit> findAllAvailableByAccommodationType(
            @Parameter(description = "Page number, default is 0")
            @RequestParam(defaultValue = "0") int page,
            @Parameter(description = "Page size, default is 20")
            @RequestParam(defaultValue = "20") int size,
            @Parameter(description = "Sorting by field (available: id, description, price), default id")
            @RequestParam(defaultValue = "id") String sort,
            @Parameter(description = "You want to rent from the date:")
            @RequestParam(value = "startTime") Instant start,
            @Parameter(description = "You want rent until the date:")
            @RequestParam("endTime") Instant end,
            @Parameter(description = "Type of unit (flat, house, apartment)")
            @PathVariable String accommodationType) {

        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
        if (start.isAfter(end)) {
            throw new IllegalArgumentException("Requested start date is after end date!");
        }
        return unitService.findAllAvailableByAccommodationType(pageable,
                AccommodationType.fromString(accommodationType), start, end);
    }

    @GetMapping("/floor/{floor}")
    @Operation(
            summary = "Get page of all available units on this floor",
            description = "Retrieve a paginated list of units, for provided date range and floor, size, and sorting"
    )
    public List<Unit> findByFloor(
            @Parameter(description = "Page number, default is 0")
            @RequestParam(defaultValue = "0") int page,
            @Parameter(description = "Page size, default is 20")
            @RequestParam(defaultValue = "20") int size,
            @Parameter(description = "Sorting by field (available: id, description, price), default id")
            @RequestParam(defaultValue = "id") String sort,
            @Parameter(description = "You want to rent from the date:")
            @RequestParam(value = "startTime") Instant start,
            @Parameter(description = "You want rent until the date:")
            @RequestParam("endTime") Instant end,
            @Parameter(description = "Integer floor of unit")
            @PathVariable Integer floor) {

        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
        if (start.isAfter(end)) {
            throw new IllegalArgumentException("Requested start date is after end date!");
        }
        return unitService.findAllAvailableByFloor(pageable, floor, start, end);
    }

    @GetMapping("/rooms/{rooms}")
    @Operation(
            summary = "Get page of all available units witch this count of rooms",
            description = "Retrieve a paginated list of units with provided count of rooms"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved units"),
            @ApiResponse(responseCode = "400", description = "Bad request due to invalid pagination, room or date parameters")
    })
    public List<Unit> findAllByNumberOfRooms(
            @Parameter(description = "Page number, default is 0")
            @RequestParam(defaultValue = "0") int page,
            @Parameter(description = "Page size, default is 20")
            @RequestParam(defaultValue = "20") int size,
            @Parameter(description = "Sorting by field (available: id, description, price), default id")
            @RequestParam(defaultValue = "id") String sort,
            @Parameter(description = "You want to rent from the date:")
            @RequestParam(value = "startTime") Instant start,
            @Parameter(description = "You want rent until the date:")
            @RequestParam("endTime") Instant end,
            @Parameter(description = "Number of rooms in unit")
            @PathVariable Integer rooms) {

        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
        if (start.isAfter(end)) {
            throw new IllegalArgumentException("Requested start date is after end date!");
        }
        return unitService.findAllAvailableByCountOfRooms(pageable, rooms, start, end);
    }


}
