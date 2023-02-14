package com.kameleoon.trialtask.api.hateoas;

import com.kameleoon.trialtask.api.controller.UserController;
import com.kameleoon.trialtask.api.controller.VoteController;
import com.kameleoon.trialtask.api.dto.response.UserResponseDTO;
import com.kameleoon.trialtask.api.dto.response.VoteResponseDTO;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;

import java.util.Collection;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Component
public class VoteResponseDTOHateoas implements DTOHateoas<VoteResponseDTO> {
    public void build(VoteResponseDTO voteResponseDTO) {
        Link self = linkTo(VoteController.class).slash(voteResponseDTO.getId()).withSelfRel();
        UserResponseDTO userResponseDTO = voteResponseDTO.getVotedUser();
        Link userSelf = linkTo(UserController.class).slash(userResponseDTO.getId()).withSelfRel();
        userResponseDTO.add(userSelf);
        voteResponseDTO.add(self);
    }

    @Override
    public CollectionModel<VoteResponseDTO> build(Collection<VoteResponseDTO> voteResponseDTOCollection) {
        for (VoteResponseDTO voteResponseDTO : voteResponseDTOCollection) {
            build(voteResponseDTO);
        }
        return CollectionModel.of(voteResponseDTOCollection);
    }
}
