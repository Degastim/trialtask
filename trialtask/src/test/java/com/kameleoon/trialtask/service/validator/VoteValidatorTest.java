package com.kameleoon.trialtask.service.validator;

import com.kameleoon.trialtask.entity.Quote;
import com.kameleoon.trialtask.entity.User;
import com.kameleoon.trialtask.entity.Vote;
import com.kameleoon.trialtask.exception.Error;
import com.kameleoon.trialtask.exception.Notification;
import com.kameleoon.trialtask.repository.dao.QuoteDao;
import com.kameleoon.trialtask.repository.dao.UserDao;
import com.kameleoon.trialtask.service.validator.Validator;
import com.kameleoon.trialtask.service.validator.VoteValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class VoteValidatorTest {
    private Validator<Vote> voteValidator;
    @Mock
    private QuoteDao quoteDao;
    @Mock
    private UserDao userDao;

    @BeforeEach
    void setUp() {
        voteValidator = new VoteValidator(quoteDao, userDao);
    }

    @Test
    void validate_notValidMark_returnNotEmptyNotification() {
        //Given
        long userId = 1;
        long quote = 1;
        Vote vote = new Vote(1L, (byte) 2, new User(userId), new Quote(quote));
        Mockito.when(userDao.existsById(userId)).thenReturn(true);
        Mockito.when(quoteDao.existsById(quote)).thenReturn(true);
        Notification expected = new Notification();
        expected.addError(new Error("vote-001", "Invalid mark", "Invalid mark"));

        //When
        Notification actual = voteValidator.validate(vote);

        //Then
        assertEquals(expected, actual);
    }

    @Test
    void validate_notValidUserId_throwException() {
        //Given
        long userId = 1;
        long quote = 1;
        Vote vote = new Vote(1L, (byte) 2, new User(userId), new Quote(quote));
        Mockito.when(userDao.existsById(userId)).thenReturn(false);
        Mockito.when(quoteDao.existsById(quote)).thenReturn(true);
        Notification expected = new Notification();
        expected.addError(new Error("vote-001", "Invalid mark", "Invalid mark"));
        expected.addError(new Error("user-001", "User with this ID not found",
                "User with this ID not found"));

        //When
        Notification actual = voteValidator.validate(vote);

        //Then
        assertEquals(expected, actual);
    }

    @Test
    void validate_notValidQuoteId_throwException() {
        //Given
        long userId = 1;
        long quote = 1;
        Vote vote = new Vote(1L, (byte) 2, new User(userId), new Quote(quote));
        Mockito.when(userDao.existsById(userId)).thenReturn(false);
        Mockito.when(quoteDao.existsById(quote)).thenReturn(false);
        Notification expected = new Notification();
        expected.addError(new Error("vote-001", "Invalid mark", "Invalid mark"));
        expected.addError(new Error("user-001", "User with this ID not found",
                "User with this ID not found"));
        expected.addError(new Error("quote-001", "Quote with this ID not found",
                "Quote with this ID not found"));

        //When
        Notification actual = voteValidator.validate(vote);

        //Then
        assertEquals(expected, actual);
    }

    @Test
    void validate_validVoteAndPositiveMark_assertDoesNotThrow() {
        //Given
        long userId = 1;
        long quoteId = 2;
        Vote vote = new Vote(1L, (byte) 1, new User(userId), new Quote(quoteId));
        Mockito.when(userDao.existsById(userId)).thenReturn(true);
        Mockito.when(quoteDao.existsById(quoteId)).thenReturn(true);

        //When-Then
        assertDoesNotThrow(() -> voteValidator.validate(vote));
    }

    @Test
    void validate_validVoteAndNegativeMark_notThrowException() {
        //Given
        long userId = 1;
        long quoteId = 2;
        Vote vote = new Vote(1L, (byte) -1, new User(userId), new Quote(quoteId));
        Mockito.when(userDao.existsById(userId)).thenReturn(true);
        Mockito.when(quoteDao.existsById(quoteId)).thenReturn(true);

        //When-Then
        assertDoesNotThrow(() -> voteValidator.validate(vote));
    }
}