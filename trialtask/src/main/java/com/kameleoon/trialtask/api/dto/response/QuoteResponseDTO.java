package com.kameleoon.trialtask.api.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Entity of a quote for response
 *
 * @author Yauheni Tsitou
 */
@AllArgsConstructor
@Getter
@Setter
public class QuoteResponseDTO extends RepresentationModel<QuoteResponseDTO> {
    private long id;
    private String content;
    private List<VoteResponseDTO> voteList;
    private UserResponseDTO postingUser;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime creationDate;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateDate;
}
