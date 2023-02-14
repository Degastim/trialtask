package com.kameleoon.trialtask.api.mapper.response;

import com.kameleoon.trialtask.api.dto.response.UserResponseDTO;
import com.kameleoon.trialtask.api.dto.response.VoteResponseDTO;
import com.kameleoon.trialtask.entity.User;
import com.kameleoon.trialtask.entity.Vote;
import org.springframework.stereotype.Component;

@Component
public class VoteResponseDTOMapper implements DTOResponseMapper<Vote, VoteResponseDTO> {
    private final DTOResponseMapper<User, UserResponseDTO> userResponseDTOMapper;

    public VoteResponseDTOMapper(DTOResponseMapper<User, UserResponseDTO> userResponseDTOMapper) {
        this.userResponseDTOMapper = userResponseDTOMapper;
    }

    @Override
    public VoteResponseDTO toDTO(Vote entity) {
        return new VoteResponseDTO(entity.getId(), entity.getMark(), userResponseDTOMapper.toDTO(entity.getVotedUser()),
                entity.getCreationDate(), entity.getUpdateDate());
    }
}
