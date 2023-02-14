package com.kameleoon.trialtask.api.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDateTime;
/**
 * Entity of a vote for response
 *
 * @author Yauheni Tsitou
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VoteResponseDTO extends RepresentationModel<VoteResponseDTO> {
    private long id;
    private byte mark;
    private UserResponseDTO votedUser;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime creationDate;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateDate;
}
