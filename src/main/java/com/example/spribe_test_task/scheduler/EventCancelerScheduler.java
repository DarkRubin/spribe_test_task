package com.example.spribe_test_task.scheduler;

import com.example.spribe_test_task.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;

@Slf4j
@EnableScheduling
@Component
@RequiredArgsConstructor
public class EventCancelerScheduler {

    @Value("${api.event-cancel-time}")
    private Duration eventCancelTime;

    private final EventRepository eventRepository;

    @Scheduled(cron = "0 * * * * *")
    public void cancelEventWithoutPayments() {
        Instant paymentDeadline = Instant.now().minus(eventCancelTime);
        Integer countDeleted = eventRepository.deleteByPaymentDeadlineExpired(paymentDeadline);
        log.debug("Deleted {} events", countDeleted);
    }
}
