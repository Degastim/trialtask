package com.kameleoon.trialtask.api.mapper.request;

/**
 * Mapper for request object to entity
 *
 * @author Yauheni Tsitou
 */
public interface DTORequestMapper<T, DTO> {
    /**
     * Maps a DTO as Entity.
     *
     * @param dto object object to map.
     * @return entity which contains the object after mapping.
     */
    T toEntity(DTO dto);
}
