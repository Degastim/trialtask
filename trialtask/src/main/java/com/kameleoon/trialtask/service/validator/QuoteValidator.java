package com.kameleoon.trialtask.service.validator;

import com.kameleoon.trialtask.repository.dao.UserDao;
import com.kameleoon.trialtask.entity.Quote;
import com.kameleoon.trialtask.exception.Error;
import com.kameleoon.trialtask.exception.Notification;
import org.springframework.stereotype.Component;

@Component
public class QuoteValidator implements Validator<Quote> {
    private final UserDao userDao;

    public QuoteValidator(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public Notification validate(Quote quote) {
        Notification notification = new Notification();
        if (!userDao.existsById(quote.getPostingUser().getId())) {
            notification.addError(new Error("quote-001", "User with this ID not found.",
                    "User with this ID not found.Specify the user id that is in the system."));
        }
        return notification;
    }
}
