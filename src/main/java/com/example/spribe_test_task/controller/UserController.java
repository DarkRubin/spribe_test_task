package com.example.spribe_test_task.controller;

import com.example.spribe_test_task.dto.UserDto;
import com.example.spribe_test_task.entity.User;
import com.example.spribe_test_task.service.UserService;
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

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Tag(name = "User Controller", description = " Controller for managing User entity, provides create and search operation")
public class UserController {

    private final UserService userService;

    @Operation(
            summary = "Get all users with pagination",
            description = "Retrieve a paginated list of users, with specified the page number, size, and sorting order",
            tags = { "User" }
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved users"),
            @ApiResponse(responseCode = "400", description = "Bad request due to invalid pagination parameters")
    })
    @GetMapping
    public ResponseEntity<List<User>> getAll(
            @Parameter(description = "Page number, default is 0")
            @RequestParam(defaultValue = "0") int page,

            @Parameter(description = "Page size, default is 20")
            @RequestParam(defaultValue = "20") int size,

            @Parameter(description = "Sorting by field (available: id, username, password), default id")
            @RequestParam(defaultValue = "id") String sort) {

        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
        return ResponseEntity.ok(userService.findAll(pageable));
    }

    @PostMapping
    @Operation(
            summary = "Create new user",
            description = "Creates new user with provided credentials (username, password)",
            tags = { "User" }
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User created successfully"),
            @ApiResponse(responseCode = "400", description = "Bad request due by invalid user data body")
    })
    public ResponseEntity<User> create(
            @Parameter(description = "Credentials of new user (username, password)", required = true) @RequestBody UserDto userDto) {
        User user = userService.create(userDto);
        return ResponseEntity.created(URI.create(user.getId().toString())).body(user);
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Find user by ID",
            description = "Finds a user by their unique identifier (ID).",
            tags = { "User" }
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User found"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    public ResponseEntity<User> findById(
            @Parameter(description = "ID of the user to be found", required = true) @PathVariable Long id) {
        return userService.findById(id).map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

}
