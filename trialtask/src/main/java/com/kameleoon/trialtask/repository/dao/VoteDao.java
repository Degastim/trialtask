package com.kameleoon.trialtask.repository.dao;

import com.kameleoon.trialtask.entity.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Interface used for interactions with vote table.
 *
 * @author Yauheni Tstiov
 */
public interface VoteDao extends JpaRepository<Vote, Long> {
    Optional<Vote> findByVotedUser_IdAndQuote_Id(long id, long id1);
}
