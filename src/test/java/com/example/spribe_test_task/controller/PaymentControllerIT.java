package com.example.spribe_test_task.controller;

import com.example.spribe_test_task.dto.PaymentDto;
import com.example.spribe_test_task.entity.Payment;
import com.example.spribe_test_task.entity.User;
import com.example.spribe_test_task.repository.PaymentRepository;
import com.example.spribe_test_task.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class PaymentControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        paymentRepository.deleteAll();
    }

    @Test
    void shouldGetAllPaymentsByUserId() throws Exception {
        User user = userRepository.save(new User("testuser", "testuser"));
        Payment payment = paymentRepository.save(new Payment("Test Payment", BigDecimal.valueOf(100), user));

        mockMvc.perform(get("/v1/payments")
                        .param("userId", user.getId().toString())
                        .param("page", "0")
                        .param("size", "10")
                        .param("sort", "id")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(payment.getId()))
                .andExpect(jsonPath("$[0].description").value(payment.getDescription()));
    }

    @Test
    void shouldCreateNewPayment() throws Exception {
        User user = userRepository.save(new User(null, "testuser"));
        PaymentDto paymentDto = new PaymentDto("New Payment", BigDecimal.valueOf(200), user.getId());

        mockMvc.perform(post("/v1/payments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(paymentDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.description").value(paymentDto.getDescription()));
    }
}