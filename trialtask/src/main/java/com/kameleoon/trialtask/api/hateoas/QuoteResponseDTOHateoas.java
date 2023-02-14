package com.kameleoon.trialtask.api.hateoas;

import com.kameleoon.trialtask.api.controller.QuoteController;
import com.kameleoon.trialtask.api.controller.UserController;
import com.kameleoon.trialtask.api.controller.VoteController;
import com.kameleoon.trialtask.api.dto.response.QuoteResponseDTO;
import com.kameleoon.trialtask.api.dto.response.UserResponseDTO;
import com.kameleoon.trialtask.api.dto.response.VoteResponseDTO;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class QuoteResponseDTOHateoas implements DTOHateoas<QuoteResponseDTO> {
    public void build(QuoteResponseDTO quoteResponseDTO) {
        Link self = linkTo(QuoteController.class).slash(quoteResponseDTO.getId()).withSelfRel();
        UserResponseDTO userResponseDTO = quoteResponseDTO.getPostingUser();
        Link userSelf = linkTo(UserController.class).slash(userResponseDTO.getId()).withSelfRel();
        List<VoteResponseDTO> voteResponseDTOList = quoteResponseDTO.getVoteList();
        if (voteResponseDTOList != null) {
            for (VoteResponseDTO voteResponseDTO : voteResponseDTOList) {
                long voteResponseDTOId = voteResponseDTO.getId();
                Link selfTagDTOLink = linkTo(methodOn(VoteController.class).find(voteResponseDTOId)).withSelfRel();
                voteResponseDTO.add(selfTagDTOLink);
            }
        }
        userResponseDTO.add(userSelf);
        quoteResponseDTO.add(self);
    }

    @Override
    public CollectionModel<QuoteResponseDTO> build(Collection<QuoteResponseDTO> quoteResponseDTOCollection) {
        for (QuoteResponseDTO quoteResponseDTO : quoteResponseDTOCollection) {
            build(quoteResponseDTO);
        }
        Link self = linkTo(methodOn(QuoteController.class).findByParam(null)).withSelfRel();
        return CollectionModel.of(quoteResponseDTOCollection, self);
    }
}
