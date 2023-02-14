package com.kameleoon.trialtask.api.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Entity of a vote for creation
 *
 * @author Yauheni Tsitou
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VoteCreationDTO {
    public byte mark;
    public long userId;
    public long quoteId;
}
