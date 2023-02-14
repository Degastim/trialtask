package com.kameleoon.trialtask.service.sorter;

import java.util.List;

/**
 * Contain operation for sorting.
 *
 * @author Yauheni Tstiov
 */
public interface Sorter<T> {
    /**
     * Operation for sorting.
     *
     * @author Yauheni Tstiov
     */
    List<T> sort(List<T> list, int number);
}
