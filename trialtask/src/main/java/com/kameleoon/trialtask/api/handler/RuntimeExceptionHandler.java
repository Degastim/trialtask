package com.kameleoon.trialtask.api.handler;

import com.kameleoon.trialtask.exception.Notification;
import com.kameleoon.trialtask.exception.ResourceNotFoundedException;
import com.kameleoon.trialtask.exception.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * The runtime exception handler.
 *
 * @author Yauheni Tstiov
 */
@ControllerAdvice
public class RuntimeExceptionHandler {
    /**
     * Handle ResourceNotFoundedException.
     *
     * @param e the exception that handler handle.
     * @return the response entity
     */
    @ExceptionHandler(ResourceNotFoundedException.class)
    public final ResponseEntity<Notification> handleResourceNotFoundedException(ResourceNotFoundedException e) {
        HttpStatus httpStatus = HttpStatus.NOT_FOUND;
        return ResponseEntity.status(httpStatus).body(e.getNotification());
    }

    /**
     * Handle ValidationException.
     *
     * @param e the exception that handler handle.
     * @return the response entity
     */
    @ExceptionHandler(ValidationException.class)
    public final ResponseEntity<Notification> handleValidationException(ValidationException e) {
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(httpStatus).body(e.getNotification());
    }
}
