package com.kameleoon.trialtask.service.validator;

import com.kameleoon.trialtask.repository.dao.QuoteDao;
import com.kameleoon.trialtask.repository.dao.UserDao;
import com.kameleoon.trialtask.entity.Vote;
import com.kameleoon.trialtask.exception.Error;
import com.kameleoon.trialtask.exception.Notification;
import org.springframework.stereotype.Component;

@Component
public class VoteValidator implements Validator<Vote> {
    private final QuoteDao quoteDao;
    private final UserDao userDao;

    public VoteValidator(QuoteDao quoteDao, UserDao userDao) {
        this.quoteDao = quoteDao;
        this.userDao = userDao;
    }

    @Override
    public Notification validate(Vote vote) {
        Notification notification = new Notification();
        if (vote.getMark() != -1 && vote.getMark() != 1) {
            notification.addError(new Error("vote-001", "Invalid mark", "Invalid mark"));
        }
        if (!userDao.existsById(vote.getVotedUser().getId())) {
            notification.addError(new Error("user-001", "User with this ID not found",
                    "User with this ID not found"));
        }
        if (!quoteDao.existsById(vote.getQuote().getId())) {
            notification.addError(new Error("quote-001", "Quote with this ID not found",
                    "Quote with this ID not found"));
        }
        return notification;
    }
}
