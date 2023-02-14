package com.kameleoon.trialtask.api.controller;

import com.kameleoon.trialtask.api.dto.request.UserCreationDTO;
import com.kameleoon.trialtask.api.dto.response.UserResponseDTO;
import com.kameleoon.trialtask.entity.User;
import com.kameleoon.trialtask.api.hateoas.UserResponseDTOHateoas;
import com.kameleoon.trialtask.api.mapper.request.DTORequestMapper;
import com.kameleoon.trialtask.api.mapper.response.DTOResponseMapper;
import com.kameleoon.trialtask.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final DTORequestMapper<User, UserCreationDTO> userCreationDTOMapper;
    private final DTOResponseMapper<User, UserResponseDTO> userResponseDTOMapper;
    private final UserResponseDTOHateoas userResponseDTOHateoas;

    public UserController(UserService userService, DTORequestMapper<User, UserCreationDTO> userCreationDTOMapper,
                          UserResponseDTOHateoas userResponseDTOHateoas,
                          DTOResponseMapper<User, UserResponseDTO> userResponseDTOMapper) {
        this.userService = userService;
        this.userCreationDTOMapper = userCreationDTOMapper;
        this.userResponseDTOHateoas = userResponseDTOHateoas;
        this.userResponseDTOMapper = userResponseDTOMapper;
    }

    @PostMapping
    private UserResponseDTO save(@RequestBody UserCreationDTO userCreationDTO) {
        User user = userCreationDTOMapper.toEntity(userCreationDTO);
        user = userService.save(user);
        UserResponseDTO userResponseDTO = userResponseDTOMapper.toDTO(user);
        userResponseDTOHateoas.build(userResponseDTO);
        return userResponseDTO;
    }

    @GetMapping("/{userId}")
    private UserResponseDTO find(@PathVariable long userId) {
        User user = userService.find(userId);
        UserResponseDTO userResponseDTO = userResponseDTOMapper.toDTO(user);
        userResponseDTOHateoas.build(userResponseDTO);
        return userResponseDTO;
    }
}
