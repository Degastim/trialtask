package com.kameleoon.trialtask.api.hateoas;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.RepresentationModel;

import java.util.Collection;

/**
 * Converter dto according to HATEOAS rules
 *
 * @author Yauheni Tstiov
 */
public interface DTOHateoas<T extends RepresentationModel<T>> {
    /**
     * Converts the dto according to the rules of HATEOAS.
     *
     * @param dto object for conversion.
     */
    void build(T dto);

    /**
     * Converts the dto collection according to the rules of HATEOAS.
     *
     * @param collection contains a dtos for conversion.
     */
    CollectionModel<T> build(Collection<T> collection);
}
