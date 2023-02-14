package com.kameleoon.trialtask.api.mapper.request;

import com.kameleoon.trialtask.api.dto.request.VoteCreationDTO;
import com.kameleoon.trialtask.entity.Quote;
import com.kameleoon.trialtask.entity.User;
import com.kameleoon.trialtask.entity.Vote;
import org.springframework.stereotype.Component;

@Component
public class VoteCreationDTOMapper implements DTORequestMapper<Vote, VoteCreationDTO> {
    @Override
    public Vote toEntity(VoteCreationDTO entity) {
        return new Vote(entity.getMark(),new User(entity.getUserId()),new Quote(entity.getQuoteId()));
    }
}
