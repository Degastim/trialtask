package com.kameleoon.trialtask.api.mapper.request;

import com.kameleoon.trialtask.api.dto.request.QuoteDataDTO;
import com.kameleoon.trialtask.entity.Quote;
import com.kameleoon.trialtask.entity.User;
import org.springframework.stereotype.Component;

@Component
public class QuoteDataDTOMapper implements DTORequestMapper<Quote, QuoteDataDTO> {
    @Override
    public Quote toEntity(QuoteDataDTO quoteDataDTO) {
        return new Quote(quoteDataDTO.getContent(),new User(quoteDataDTO.getUserId()));
    }
}
