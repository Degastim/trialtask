package com.kameleoon.trialtask.api.mapper.response;

import com.kameleoon.trialtask.api.dto.response.QuoteResponseDTO;
import com.kameleoon.trialtask.api.dto.response.UserResponseDTO;
import com.kameleoon.trialtask.api.dto.response.VoteResponseDTO;
import com.kameleoon.trialtask.entity.Quote;
import com.kameleoon.trialtask.entity.User;
import com.kameleoon.trialtask.entity.Vote;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class QuoteResponseDTOResponseMapperTest {
    private DTOResponseMapper<Quote, QuoteResponseDTO> mapper;
    @Mock
    private DTOResponseMapper<Vote, VoteResponseDTO> voteResponseDTOMapper;
    @Mock
    private DTOResponseMapper<User, UserResponseDTO> userResponseDTOMapper;

    @BeforeEach
    void setUp() {
        mapper = new QuoteResponseDTOResponseMapper(voteResponseDTOMapper, userResponseDTOMapper);
    }

    @Test
    void toDTO() {
        //Given
        long quoteId = 1;
        String content = "Hello";
        LocalDateTime creationDate = LocalDateTime.now();
        LocalDateTime updateDate = LocalDateTime.now();
        Quote quote = new Quote(quoteId, content, null, null, creationDate, updateDate);
        QuoteResponseDTO expected = new QuoteResponseDTO(quoteId, content, new ArrayList<>(), null,
                creationDate, updateDate);

        //When
        QuoteResponseDTO actual = mapper.toDTO(quote);

        //Then
        assertEquals(expected, actual);
    }
}