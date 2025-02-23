package com.example.spribe_test_task.service;

import com.example.spribe_test_task.dto.UserDto;
import com.example.spribe_test_task.entity.User;
import com.example.spribe_test_task.mapper.UserMapper;
import com.example.spribe_test_task.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public List<User> findAll(Pageable pageable) {
        return userRepository.findAll(pageable).getContent();
    }

    public User create(UserDto dto) {
        User user = userMapper.toEntity(dto);
        return userRepository.save(user);
    }

    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }
}
