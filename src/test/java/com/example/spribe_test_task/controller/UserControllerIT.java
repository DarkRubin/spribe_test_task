package com.example.spribe_test_task.controller;

import com.example.spribe_test_task.config.TestDatasourceConfig;
import com.example.spribe_test_task.dto.UserDto;
import com.example.spribe_test_task.entity.User;
import com.example.spribe_test_task.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
    classes = TestDatasourceConfig.class)
@Testcontainers
@AutoConfigureMockMvc
public class UserControllerIT {


    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;


    @BeforeEach
    void setup() {
        userRepository.deleteAll();
    }

    @Test
    void testGetAllUsers() throws Exception {
        User user1 = new User("user1", "password1");
        User user2 = new User("user2", "password2");

        userRepository.saveAll(List.of(user1, user2));

        mockMvc.perform(get("/v1/users")
                        .param("page", "0").param("size", "10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    void testCreateUser() throws Exception {
        UserDto userDto = new UserDto("newUser", "newPassword");

        mockMvc.perform(post("/v1/users")
                        .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(userDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.username").value("newUser"));
    }

    @Test
    void testFindUserById() throws Exception {
        User user = new User("testUser", "testPassword");
        user = userRepository.save(user);

        mockMvc.perform(get("/v1/users/{id}", user.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("testUser"));
    }

    @Test
    void testFindUserById_NotFound() throws Exception {
        mockMvc.perform(get("/v1/users/{id}", 999))
                .andExpect(status().isNotFound());
    }
}

