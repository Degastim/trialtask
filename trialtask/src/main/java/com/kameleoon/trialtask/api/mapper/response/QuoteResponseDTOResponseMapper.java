package com.kameleoon.trialtask.api.mapper.response;

import com.kameleoon.trialtask.api.dto.response.QuoteResponseDTO;
import com.kameleoon.trialtask.api.dto.response.UserResponseDTO;
import com.kameleoon.trialtask.api.dto.response.VoteResponseDTO;
import com.kameleoon.trialtask.entity.Quote;
import com.kameleoon.trialtask.entity.User;
import com.kameleoon.trialtask.entity.Vote;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class QuoteResponseDTOResponseMapper implements DTOResponseMapper<Quote, QuoteResponseDTO> {
    private final DTOResponseMapper<Vote, VoteResponseDTO> voteResponseDTOMapper;
    private final DTOResponseMapper<User, UserResponseDTO> userResponseDTOMapper;

    public QuoteResponseDTOResponseMapper(DTOResponseMapper<Vote, VoteResponseDTO> voteResponseDTOMapper,
                                          DTOResponseMapper<User, UserResponseDTO> userResponseDTOMapper) {
        this.voteResponseDTOMapper = voteResponseDTOMapper;
        this.userResponseDTOMapper = userResponseDTOMapper;
    }

    @Override
    public QuoteResponseDTO toDTO(Quote entity) {
        List<VoteResponseDTO> voteDTOList = new ArrayList<>();
        if (entity.getVoteList() != null) {
            voteDTOList = entity.getVoteList().stream().map(voteResponseDTOMapper::toDTO).toList();
        }
        User postingUser = entity.getPostingUser();
        UserResponseDTO userResponseDTO = null;
        if (postingUser != null) {
            userResponseDTO = userResponseDTOMapper.toDTO(entity.getPostingUser());
        }
        return new QuoteResponseDTO(entity.getId(), entity.getContent(), voteDTOList, userResponseDTO,
                entity.getCreationDate(), entity.getUpdateDate());
    }
}
