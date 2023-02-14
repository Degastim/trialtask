package com.kameleoon.trialtask.exception;

import lombok.Getter;

/**
 * Custom Validation exception.
 *
 * @author Yauheni Tsitov
 */
@Getter
public class ValidationException extends RuntimeException {
    private final Notification notification;

    public ValidationException(Notification notification) {
        this.notification = notification;
    }
}
