package com.kameleoon.trialtask.api.mapper.response;

import com.kameleoon.trialtask.api.dto.response.UserResponseDTO;
import com.kameleoon.trialtask.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserResponseDTOMapperTest {
    private DTOResponseMapper<User, UserResponseDTO> mapper;

    @BeforeEach
    void setUp() {
        mapper = new UserResponseDTOMapper();
    }

    @Test
    void toDTO() {
        //Given
        long userId = 1;
        String name = "Zhenya";
        String email = "zhenya.titov1@gmail.com";
        LocalDateTime creationDate = LocalDateTime.now();
        LocalDateTime updateDate = LocalDateTime.now();
        User user = new User(userId, name, email, creationDate, updateDate);
        UserResponseDTO expected = new UserResponseDTO(userId, name, email, creationDate, updateDate);

        //When
        UserResponseDTO actual = mapper.toDTO(user);

        //Then
        assertEquals(expected, actual);
    }
}