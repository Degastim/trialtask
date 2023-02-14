package com.kameleoon.trialtask.exception;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Contain information of exception
 *
 * @author Yauheni Tsitou
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Error {
    private String error;
    private String message;
    private String detail;
}
