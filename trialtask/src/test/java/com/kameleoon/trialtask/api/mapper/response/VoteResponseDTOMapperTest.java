package com.kameleoon.trialtask.api.mapper.response;

import com.kameleoon.trialtask.api.dto.response.UserResponseDTO;
import com.kameleoon.trialtask.api.dto.response.VoteResponseDTO;
import com.kameleoon.trialtask.entity.Quote;
import com.kameleoon.trialtask.entity.User;
import com.kameleoon.trialtask.entity.Vote;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class VoteResponseDTOMapperTest {
    private DTOResponseMapper<Vote, VoteResponseDTO> mapper;
    @Mock
    private DTOResponseMapper<User, UserResponseDTO> userResponseDTOMapper;

    @BeforeEach
    void setUp() {
        mapper = new VoteResponseDTOMapper(userResponseDTOMapper);
    }

    @Test
    void toDTO() {
        //Given
        long voteId = 1;
        byte mark = 1;
        User votedUser = new User();
        LocalDateTime creationDate = LocalDateTime.now();
        LocalDateTime updateDate = LocalDateTime.now();
        Mockito.when(userResponseDTOMapper.toDTO(votedUser)).thenReturn(new UserResponseDTO());
        Vote vote = new Vote(voteId, mark, new User(), new Quote(), creationDate, updateDate);
        VoteResponseDTO expected = new VoteResponseDTO(voteId, mark, new UserResponseDTO(),
                creationDate, updateDate);

        //When
        VoteResponseDTO actual = mapper.toDTO(vote);

        //Then
        assertEquals(expected, actual);
    }
}