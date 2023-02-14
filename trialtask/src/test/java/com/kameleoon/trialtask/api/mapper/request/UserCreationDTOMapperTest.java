package com.kameleoon.trialtask.api.mapper.request;

import com.kameleoon.trialtask.api.dto.request.UserCreationDTO;
import com.kameleoon.trialtask.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserCreationDTOMapperTest {

    private DTORequestMapper<User, UserCreationDTO> mapper;

    @BeforeEach
    void setUp() {
        mapper = new UserCreationDTOMapper();
    }

    @Test
    void toEntity() {
        //Given
        String name = "Zhenya";
        String password = "Qwerty#123";
        String email = "zhenya@gmail.com";
        User expected = new User(name, password, email);
        UserCreationDTO userCreationDTO = new UserCreationDTO(name, email, password);

        //When
        User actual = mapper.toEntity(userCreationDTO);

        //Then
        assertEquals(expected, actual);
    }
}