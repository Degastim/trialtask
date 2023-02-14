package com.kameleoon.trialtask.api.mapper.response;

import com.kameleoon.trialtask.api.dto.response.UserResponseDTO;
import com.kameleoon.trialtask.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserResponseDTOMapper implements DTOResponseMapper<User, UserResponseDTO> {
    @Override
    public UserResponseDTO toDTO(User entity) {
        return new UserResponseDTO(entity.getId(), entity.getName(), entity.getEmail(), entity.getCreationDate(),
                entity.getUpdateDate());
    }
}
