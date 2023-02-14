package com.kameleoon.trialtask.service.validator;

import com.kameleoon.trialtask.entity.Quote;
import com.kameleoon.trialtask.entity.User;
import com.kameleoon.trialtask.exception.Error;
import com.kameleoon.trialtask.exception.Notification;
import com.kameleoon.trialtask.repository.dao.UserDao;
import com.kameleoon.trialtask.service.validator.QuoteValidator;
import com.kameleoon.trialtask.service.validator.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class QuoteValidatorTest {
    private Validator<Quote> quoteValidator;
    @Mock
    private UserDao userDao;

    @BeforeEach
    void setUp() {
        quoteValidator = new QuoteValidator(userDao);
    }

    @Test
    void validate_notValidQuoteId_throwException() {
        //Given
        long userId = 1;
        Quote quote = new Quote("Two beer or not two beer", new User(userId));
        Mockito.when(userDao.existsById(userId)).thenReturn(false);
        Notification expected = new Notification();
        expected.addError(new Error("quote-001", "User with this ID not found.",
                "User with this ID not found.Specify the user id that is in the system."));

        //When
        Notification actual = quoteValidator.validate(quote);

        //Then
        assertEquals(expected, actual);
    }

    @Test
    void validate_validQuote_notThrowException() {
        //Given
        long userId = 1;
        Quote quote = new Quote("Two beer or not two beer", new User(userId));
        Mockito.when(userDao.existsById(userId)).thenReturn(true);
        Notification expected = new Notification();

        //When
        Notification actual = quoteValidator.validate(quote);

        //Then
        assertEquals(expected, actual);
    }
}