package com.example.spribe_test_task.controller;

import com.example.spribe_test_task.dto.UnitDto;
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
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.net.URI;
import java.util.List;


@RestController
@RequestMapping("/v1/units")
@RequiredArgsConstructor
@Tag(name = "Unit Controller", description = """
        Controller for manage Unit entity, provide create,
         update and search by different properties (like, availability, floor,
         accommodation type or count of rooms) operations""")
public class UnitController {

    private final UnitService unitService;

    @GetMapping
    @Operation(
            summary = "Get all units with pagination",
            description = "Retrieve a paginated list of units, with specified the page number, size, and sorting",
            tags = { "Unit" }
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved units"),
            @ApiResponse(responseCode = "400", description = "Bad request due to invalid pagination parameters")
    })
    public ResponseEntity<List<Unit>> getAll(
            @Parameter(description = "Page number, default is 0")
            @RequestParam(defaultValue = "0") int page,
            @Parameter(description = "Page size, default is 20")
            @RequestParam(defaultValue = "20") int size,
            @Parameter(description = "Sorting by field (available: id, description, price), default id")
            @RequestParam(defaultValue = "id") String sort) {

        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
        return ResponseEntity.ok(unitService.findAll(pageable));
    }

    @GetMapping("/price")
    @Operation(
            summary = "Get units suitable for the provided price range with pagination",
            description = """ 
                     Retrieve a paginated list of units in which the price between provided diapason,
                      with specified the page number, size, and sorting""",
            tags = { "Unit" }
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved units"),
            @ApiResponse(responseCode = "400", description = "Bad request due to invalid pagination, or price parameters")
    })
    public ResponseEntity<List<Unit>> findByPriceRange(
            @Parameter(description = "Page number, default is 0")
            @RequestParam(defaultValue = "0") int page,
            @Parameter(description = "Page size, default is 20")
            @RequestParam(defaultValue = "20") int size,
            @Parameter(description = "Sorting by field (available: id, description, price), default id")
            @RequestParam(defaultValue = "id") String sort,
            @Parameter(description = "Numeric value of minimal price, default is 0")
            @RequestParam(value = "minPrice", defaultValue = "0") BigDecimal min,
            @Parameter(description = "Numeric value of maximum price, default is 5000")
            @RequestParam(value = "maxPrice", defaultValue = "5000") BigDecimal max) {

        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
        return ResponseEntity.ok(unitService.findByPriceBetween(pageable, min, max));
    }

    @PostMapping
    @Operation(
            summary = "Create new unit",
            description = "Create new unit with provided description, properties and cost",
            tags = { "Unit" }
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Unit created successful"),
            @ApiResponse(responseCode = "400", description = "Bad request due invalid unit data body")
    })
    public ResponseEntity<Unit> create(
            @Parameter(description = "Information about new unit (description, properties, cost)")
            @RequestBody UnitDto unitDto) {
        Unit unit = unitService.create(unitDto);
        return ResponseEntity.created(URI.create(unit.getId().toString())).body(unit);
    }

    @GetMapping("/type/{accommodationType}")
    @Operation(
            summary = "Get page of all units of this type",
            description = "Retrieve a paginated list of units with provided type of accommodation"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved units"),
            @ApiResponse(responseCode = "400", description = "Bad request due to invalid pagination, or type parameters")
    })
    public ResponseEntity<List<Unit>> findAllAvailableByAccommodationType(
            @Parameter(description = "Page number, default is 0")
            @RequestParam(defaultValue = "0") int page,
            @Parameter(description = "Page size, default is 20")
            @RequestParam(defaultValue = "20") int size,
            @Parameter(description = "Sorting by field (available: id, description, price), default id")
            @RequestParam(defaultValue = "id") String sort,
            @Parameter(description = "Type of unit (flat, house, apartment)")
            @PathVariable String accommodationType) {

        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
        return ResponseEntity.ok(unitService.findByAccommodationType(pageable, AccommodationType.fromString(accommodationType)));
    }

    @GetMapping("/floor/{floor}")
    @Operation(
            summary = "Get page of all units on this floor",
            description = "Retrieve a paginated list of units in which provided floor"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved units"),
            @ApiResponse(responseCode = "400", description = "Bad request due to invalid pagination, or floor parameters")
    })
    public ResponseEntity<List<Unit>> findByFloor(
            @Parameter(description = "Page number, default is 0")
            @RequestParam(defaultValue = "0") int page,
            @Parameter(description = "Page size, default is 20")
            @RequestParam(defaultValue = "20") int size,
            @Parameter(description = "Sorting by field (available: id, description, price), default id")
            @RequestParam(defaultValue = "id") String sort,
            @Parameter(description = "Integer floor of unit")
            @PathVariable Integer floor) {

        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
        return ResponseEntity.ok(unitService.findByFloor(pageable, floor));
    }

    @GetMapping("/rooms/{rooms}")
    @Operation(
            summary = "Get page of all units witch this count of rooms",
            description = "Retrieve a paginated list of units with provided count of rooms"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved units"),
            @ApiResponse(responseCode = "400", description = "Bad request due to invalid pagination, or room parameters")
    })
    public ResponseEntity<List<Unit>> findByNumberOfRoom(
            @Parameter(description = "Page number, default is 0")
            @RequestParam(defaultValue = "0") int page,
            @Parameter(description = "Page size, default is 20")
            @RequestParam(defaultValue = "20") int size,
            @Parameter(description = "Sorting by field (available: id, description, price), default id")
            @RequestParam(defaultValue = "id") String sort,
            @Parameter(description = "Number of rooms in unit")
            @PathVariable Integer rooms) {

        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
        return ResponseEntity.ok(unitService.findByNumberOfRooms(pageable, rooms));
    }

}
