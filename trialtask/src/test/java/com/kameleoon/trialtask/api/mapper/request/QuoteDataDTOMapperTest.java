package com.kameleoon.trialtask.api.mapper.request;

import com.kameleoon.trialtask.api.dto.request.QuoteDataDTO;
import com.kameleoon.trialtask.entity.Quote;
import com.kameleoon.trialtask.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class QuoteDataDTOMapperTest {
    private DTORequestMapper<Quote, QuoteDataDTO> mapper;

    @BeforeEach
    void setUp() {
        mapper = new QuoteDataDTOMapper();
    }

    @Test
    void toEntity() {
        //Given
        QuoteDataDTO quoteDataDTO = new QuoteDataDTO(3, "One,Two,Three");
        Quote expected = new Quote("One,Two,Three", new User(3));

        //When
        Quote actual = mapper.toEntity(quoteDataDTO);

        //Then
        assertEquals(expected, actual);
    }
}