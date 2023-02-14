package com.kameleoon.trialtask.api.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDateTime;

/**
 * Entity of a user for response
 *
 * @author Yauheni Tsitou
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDTO extends RepresentationModel<UserResponseDTO> {
    private long id;
    private String name;
    private String email;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime creationDate;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateDate;
}
