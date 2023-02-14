package com.kameleoon.trialtask.api.dto.request;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * Entity of a user for creation
 *
 * @author Yauheni Tsitou
 */
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class UserCreationDTO {
    private String login;
    private String email;
    private String password;
}
