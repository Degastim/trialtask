package com.kameleoon.trialtask.api.mapper.request;

import com.kameleoon.trialtask.api.dto.request.VoteCreationDTO;
import com.kameleoon.trialtask.entity.Quote;
import com.kameleoon.trialtask.entity.User;
import com.kameleoon.trialtask.entity.Vote;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class VoteCreationDTOMapperTest {
    private DTORequestMapper<Vote, VoteCreationDTO> mapper;

    @BeforeEach
    void setUp() {
        mapper = new VoteCreationDTOMapper();
    }

    @Test
    void toEntity() {
        //Given
        byte mark = 1;
        long userId = 1;
        long quoteId = 1;
        VoteCreationDTO voteCreationDTO = new VoteCreationDTO(mark, userId, quoteId);
        Vote expected = new Vote(mark, new User(userId), new Quote(quoteId));

        //When
        Vote actual = mapper.toEntity(voteCreationDTO);

        //Then
        assertEquals(expected, actual);
    }
}