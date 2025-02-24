package com.example.spribe_test_task.controller;

import com.example.spribe_test_task.dto.PaymentDto;
import com.example.spribe_test_task.entity.Payment;
import com.example.spribe_test_task.service.PaymentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/payments")
@RequiredArgsConstructor
@Tag(name = "Payment Controller", description = "Controller to manage Payments provide creating and find history operations")
public class PaymentController {

    private final PaymentService paymentService;

    @GetMapping
    @Operation(
            summary = "Get all payments of provided user with pagination",
            description = "Retrieve a paginated list of payments of provided user, with specified the page number, size, and sorting",
            tags = { "Payment" }
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved payments"),
            @ApiResponse(responseCode = "400", description = "Bad request due to invalid pagination parameters or user ID")
    })
    public List<Payment> findAllByUserId(
            @Parameter(description = "Page number, default is 0")
            @RequestParam(defaultValue = "0") int page,
            @Parameter(description = "Page size, default is 20")
            @RequestParam(defaultValue = "20") int size,
            @Parameter(description = "Sorting by field (available: id, description, price), default id")
            @RequestParam(defaultValue = "id") String sort,
            @Parameter(description = "ID of user")
            @RequestParam Long userId) {

        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
        return paymentService.findAllByUser(pageable, userId);
    }

    @PostMapping
    @Operation(
            summary = "Create new payment",
            description = "Creates new payment with provided IDs of event and user",
            tags = { "Payment" }
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User created successfully"),
            @ApiResponse(responseCode = "400", description = "Bad request due by invalid payment data body")
    })
    public Payment pay(@RequestBody PaymentDto paymentDto) {
        return paymentService.save(paymentDto);
    }

}
