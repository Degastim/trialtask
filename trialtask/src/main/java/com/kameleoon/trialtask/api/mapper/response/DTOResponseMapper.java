package com.kameleoon.trialtask.api.mapper.response;

/**
 * Mapper for entity to response object
 *
 * @author Yauheni Tsitou
 */
public interface DTOResponseMapper<T, DTO> {
    /**
     * Maps an Entity as DTO .
     *
     * @param entity object object to map.
     * @return dto which contains the object after mapping.
     */
    DTO toDTO(T entity);
}
