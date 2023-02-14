package com.kameleoon.trialtask.api.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Container for parameter for searching.
 *
 * @author Yauheni Tsitou
 */
@AllArgsConstructor
@Getter
@Setter
public class ParamContainer {
    private String type;
    private Integer number;
    private Integer page;
    private Integer size;
}
