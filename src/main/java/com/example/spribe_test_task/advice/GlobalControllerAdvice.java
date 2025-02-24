package com.example.spribe_test_task.advice;

import com.example.spribe_test_task.entity.Event;
import com.example.spribe_test_task.excption.EventCreationException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLException;
import java.util.Map;

@RestControllerAdvice
public class GlobalControllerAdvice {

    @ExceptionHandler(PropertyReferenceException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handlePropertyReferenceException(PropertyReferenceException ex) {
        return Map.of("message", ex.getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handlePropertyReferenceException(IllegalArgumentException ex) {
        return Map.of("message", ex.getMessage());
    }

    @ExceptionHandler(EventCreationException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public Map<String, String> handleEventCreationException(EventCreationException ex) {
        return Map.of("message", ex.getMessage());
    }

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handlePropertyReferenceException(EntityNotFoundException ex) {
        return Map.of("message", "Entity not found");
    }

    @ExceptionHandler(SQLException.class)
    public ResponseEntity<Map<String, String>> handleSQLException(SQLException ex) {
        if (ex.getSQLState().equals("23505")) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(Map.of("message", "User cannot book one unit twice"));
        } else if (ex.getSQLState().equals("23503")) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(Map.of("message", "This unit already payed. Can't cancel payment"));
        } else {
            return ResponseEntity.internalServerError().body(Map.of("message", ex.getMessage()));
        }
    }

}
