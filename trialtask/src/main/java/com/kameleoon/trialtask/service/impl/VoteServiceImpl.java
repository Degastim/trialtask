package com.kameleoon.trialtask.service.impl;

import com.kameleoon.trialtask.entity.User;
import com.kameleoon.trialtask.entity.Vote;
import com.kameleoon.trialtask.exception.Error;
import com.kameleoon.trialtask.exception.Notification;
import com.kameleoon.trialtask.exception.ResourceNotFoundedException;
import com.kameleoon.trialtask.exception.ValidationException;
import com.kameleoon.trialtask.repository.dao.UserDao;
import com.kameleoon.trialtask.repository.dao.VoteDao;
import com.kameleoon.trialtask.service.VoteService;
import com.kameleoon.trialtask.service.validator.Validator;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VoteServiceImpl implements VoteService {
    private final VoteDao voteDao;
    private final UserDao userDao;
    private final Validator<Vote> voteValidator;

    public VoteServiceImpl(VoteDao voteDao, UserDao userDao, Validator<Vote> voteValidator) {
        this.voteDao = voteDao;
        this.userDao = userDao;
        this.voteValidator = voteValidator;
    }

    @Override
    public Vote find(long voteId) {
        Optional<Vote> optionalQuote = voteDao.findById(voteId);
        if (optionalQuote.isEmpty()) {
            Notification notification = new Notification();
            notification.addError(new Error("vote-02", "Vote with this ID not found",
                    "Vote with this ID not found"));
            throw new ResourceNotFoundedException(notification);
        }
        return optionalQuote.get();
    }

    @Override
    public void create(Vote vote) {
        Notification notification=voteValidator.validate(vote);
        if (notification.hasErrors()) {
            throw new ValidationException(notification);
        }
        Optional<Vote> optionalDaoVote =
                voteDao.findByVotedUser_IdAndQuote_Id(vote.getVotedUser().getId(), vote.getQuote().getId());
        if (optionalDaoVote.isPresent()) {
            Vote daoVote = optionalDaoVote.get();
            vote.setId(daoVote.getId());
        }
        voteDao.save(vote);
        User user = userDao.findById(vote.getVotedUser().getId()).get();
        vote.setVotedUser(user);
    }
}
