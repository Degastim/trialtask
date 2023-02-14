package com.kameleoon.trialtask.api.hateoas;

import com.kameleoon.trialtask.api.controller.UserController;
import com.kameleoon.trialtask.api.dto.response.UserResponseDTO;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;

import java.util.Collection;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Component
public class UserResponseDTOHateoas implements DTOHateoas<UserResponseDTO> {
    public void build(UserResponseDTO userResponseDTO) {
        Link self = linkTo(UserController.class).slash(userResponseDTO.getId()).withSelfRel();
        userResponseDTO.add(self);
    }

    @Override
    public CollectionModel<UserResponseDTO> build(Collection<UserResponseDTO> userResponseDTOCollection) {
        for (UserResponseDTO userResponseDTO : userResponseDTOCollection) {
            build(userResponseDTO);
        }
        return CollectionModel.of(userResponseDTOCollection);
    }
}
