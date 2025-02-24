package com.example.spribe_test_task.service;

import com.example.spribe_test_task.dto.PaymentDto;
import com.example.spribe_test_task.entity.Event;
import com.example.spribe_test_task.entity.Payment;
import com.example.spribe_test_task.entity.User;
import com.example.spribe_test_task.repository.EventRepository;
import com.example.spribe_test_task.repository.PaymentRepository;
import com.example.spribe_test_task.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final EventRepository eventRepository;
    private final UserRepository userRepository;

    public List<Payment> findAllByUser(Pageable pageable, Long userId) {
        return paymentRepository.findAllByUser_Id(pageable, userId).getContent();
    }

    @Transactional
    public Payment save(PaymentDto paymentDto) {
        Event event = eventRepository.findById(paymentDto.getEventId())
                .orElseThrow(EntityNotFoundException::new);
        User user = userRepository.findById(paymentDto.getUserId())
                .orElseThrow(EntityNotFoundException::new);

        if (!event.getUser().getId().equals(user.getId())) {
            throw new IllegalArgumentException("User id not match");
        }

        Payment payment = new Payment(event, user);
        event.setPayment(payment);
        return paymentRepository.save(payment);
    }



}
