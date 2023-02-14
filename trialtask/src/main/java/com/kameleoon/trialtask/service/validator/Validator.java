package com.kameleoon.trialtask.service.validator;

import com.kameleoon.trialtask.exception.Notification;

/**
 * Validator
 *
 * @author Yauheni Tstiov
 */
public interface Validator<T> {
    Notification validate(T obj);
}
