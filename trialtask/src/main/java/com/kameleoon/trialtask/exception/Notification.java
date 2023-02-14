package com.kameleoon.trialtask.exception;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * Contain information of exceptions
 *
 * @author Yauheni Tsitou
 */
@Getter
@EqualsAndHashCode
public class Notification {
    private final List<Error> errors = new ArrayList<>();

    public void addError(Error error) {
        errors.add(error);
    }

    public boolean hasErrors() {
        return !errors.isEmpty();
    }
}
