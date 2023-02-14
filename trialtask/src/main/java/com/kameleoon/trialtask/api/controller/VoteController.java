package com.kameleoon.trialtask.api.controller;

import com.kameleoon.trialtask.api.dto.request.VoteCreationDTO;
import com.kameleoon.trialtask.api.dto.response.VoteResponseDTO;
import com.kameleoon.trialtask.entity.Vote;
import com.kameleoon.trialtask.api.hateoas.VoteResponseDTOHateoas;
import com.kameleoon.trialtask.api.mapper.request.DTORequestMapper;
import com.kameleoon.trialtask.api.mapper.response.DTOResponseMapper;
import com.kameleoon.trialtask.service.VoteService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/votes")
public class VoteController {
    private final VoteService voteService;
    private final DTOResponseMapper<Vote, VoteResponseDTO> voteResponseDTOMapper;
    private final VoteResponseDTOHateoas voteResponseDTOHateoas;
    private final DTORequestMapper<Vote, VoteCreationDTO> voteCreationDTOMapper;

    public VoteController(VoteService voteService, DTOResponseMapper<Vote, VoteResponseDTO> voteResponseDTOMapper,
                          VoteResponseDTOHateoas voteResponseDTOHateoas,
                          DTORequestMapper<Vote, VoteCreationDTO> voteCreationDTOMapper) {
        this.voteService = voteService;
        this.voteResponseDTOMapper = voteResponseDTOMapper;
        this.voteResponseDTOHateoas = voteResponseDTOHateoas;
        this.voteCreationDTOMapper = voteCreationDTOMapper;
    }

    @GetMapping("/{voteId}")
    public VoteResponseDTO find(@PathVariable long voteId) {
        Vote vote = voteService.find(voteId);
        VoteResponseDTO voteResponseDTO = voteResponseDTOMapper.toDTO(vote);
        voteResponseDTOHateoas.build(voteResponseDTO);
        return voteResponseDTO;
    }

    @GetMapping
    public VoteResponseDTO findAll() {
        return null;
    }


    @PostMapping
    public VoteResponseDTO create(@RequestBody VoteCreationDTO voteCreationDTO) {
        Vote vote = voteCreationDTOMapper.toEntity(voteCreationDTO);
        voteService.create(vote);
        VoteResponseDTO voteResponseDTO = voteResponseDTOMapper.toDTO(vote);
        voteResponseDTOHateoas.build(voteResponseDTO);
        return voteResponseDTO;
    }
}

