package com.kameleoon.trialtask.service;

import com.kameleoon.trialtask.entity.Vote;

/**
 * Quote service for votes
 *
 * @author Yauheni Tstiov
 */
public interface VoteService {
    Vote find(long voteId);

    void create(Vote vote);
}
