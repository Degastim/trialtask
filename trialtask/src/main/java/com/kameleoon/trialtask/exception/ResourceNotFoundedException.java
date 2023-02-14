package com.kameleoon.trialtask.exception;

import lombok.Getter;

/**
 * Custom ResourceNotFounded exception.
 *
 * @author Yauheni Tsitov
 */
@Getter
public class ResourceNotFoundedException extends RuntimeException {
    private final Notification notification;

    public ResourceNotFoundedException(Notification notification) {
        this.notification = notification;
    }
}