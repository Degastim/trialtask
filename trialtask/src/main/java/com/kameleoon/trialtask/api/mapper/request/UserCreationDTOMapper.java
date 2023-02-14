package com.kameleoon.trialtask.api.mapper.request;

import com.kameleoon.trialtask.api.dto.request.UserCreationDTO;
import com.kameleoon.trialtask.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserCreationDTOMapper implements DTORequestMapper<User, UserCreationDTO> {
    @Override
    public User toEntity(UserCreationDTO userCreationDTO) {
        return new User(userCreationDTO.getLogin(), userCreationDTO.getPassword(), userCreationDTO.getEmail());
    }
}
