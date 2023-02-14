package com.kameleoon.trialtask.service.impl;

import com.kameleoon.trialtask.api.dto.request.ParamContainer;
import com.kameleoon.trialtask.entity.Quote;
import com.kameleoon.trialtask.entity.User;
import com.kameleoon.trialtask.exception.Error;
import com.kameleoon.trialtask.exception.Notification;
import com.kameleoon.trialtask.exception.ResourceNotFoundedException;
import com.kameleoon.trialtask.exception.ValidationException;
import com.kameleoon.trialtask.repository.dao.QuoteDao;
import com.kameleoon.trialtask.repository.dao.UserDao;
import com.kameleoon.trialtask.service.QuoteService;
import com.kameleoon.trialtask.service.sorter.quote.QuoteSortOperation;
import com.kameleoon.trialtask.service.validator.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


@ExtendWith(MockitoExtension.class)
class QuoteServiceImplTest {
    private QuoteService quoteService;
    @Mock
    private QuoteDao quoteDao;
    @Mock
    private UserDao userDao;
    @Mock
    private Validator<Quote> quoteValidator;
    @Mock
    private Validator<ParamContainer> paramContainerValidator;
    @Mock
    private QuoteSortOperation quoteSortOperation;

    @BeforeEach
    void setUp() {
        quoteService = new QuoteServiceImpl(quoteDao, userDao, quoteValidator, paramContainerValidator, quoteSortOperation);
    }

    @Test
    void save_notValidQuote_throwException() {
        //Given
        Quote quote = new Quote();
        Notification notification = new Notification();
        notification.addError(new Error());
        Mockito.when(quoteValidator.validate(quote)).thenReturn(notification);

        //When-Then
        assertThrows(ValidationException.class, () -> quoteService.save(quote));
    }

    @Test
    void save_validQuote_throwException() {
        //Given
        long quoteId = 1;
        Quote quote = new Quote(1, "Hi", new User(1));
        Mockito.when(quoteValidator.validate(quote)).thenReturn(new Notification());
        Mockito.when(userDao.findById(quote.getPostingUser().getId())).thenReturn(Optional.of(new User(1)));
        Quote expected = new Quote(quoteId, "Hi", new User(1));

        //When
        Quote actual = quoteService.save(quote);

        //Then
        assertEquals(expected, actual);
    }

    @Test
    void find_invalidId_throwsException() {
        //Given
        long quoteId = 1;
        Mockito.when(quoteDao.findById(quoteId)).thenReturn(Optional.empty());

        //When-Then
        assertThrows(ResourceNotFoundedException.class, () -> quoteService.find(quoteId));
    }

    @Test
    void find_validId_returnObject() {
        //Given
        long quoteId = 1;
        Quote quote = new Quote(quoteId);
        Mockito.when(quoteDao.findById(quoteId)).thenReturn(Optional.of(quote));
        Quote expected = new Quote(quoteId);

        //When
        Quote actual = quoteService.find(quoteId);

        //Then
        assertEquals(expected, actual);
    }

    @Test
    void findByParams_notValidContainer_throwsException() {
        //Given
        ParamContainer paramContainer = new ParamContainer("", 0, 0, 0);
        Mockito.doThrow(ValidationException.class).when(paramContainerValidator).validate(paramContainer);

        //When-Then
        assertThrows(ValidationException.class, () -> quoteService.findByParams(paramContainer));
    }

    @Test
    void delete_notValidId_throwsException() {
        //Given
        long quoteId = 1;
        Mockito.when(quoteDao.existsById(quoteId)).thenReturn(false);

        //When-Then
        assertThrows(ResourceNotFoundedException.class, () -> quoteService.delete(quoteId));
    }

    @Test
    void delete_validId_notThrowsException() {
        //Given
        long quoteId = 1;
        Mockito.when(quoteDao.existsById(quoteId)).thenReturn(true);

        //When-Then
        assertDoesNotThrow(() -> quoteService.delete(quoteId));
    }
}