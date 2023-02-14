package com.kameleoon.trialtask.repository.dao;

import com.kameleoon.trialtask.config.TestConfiguration;
import com.kameleoon.trialtask.entity.Quote;
import com.kameleoon.trialtask.entity.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {TestConfiguration.class})
@Transactional
class QuoteDaoTest {

    private final QuoteDao quoteDao;

    @Autowired
    private QuoteDaoTest(QuoteDao quoteDao) {
        this.quoteDao = quoteDao;
    }

    @Test
    void findById() {
        //Given
        long quoteId = 1;
        LocalDateTime localDateTime = LocalDateTime.of(2013, 9, 13, 12, 12, 12);
        User user = new User(1, "Zhenya", "zhenya@gmail.com", "123jgke43g1rkrg1rijriw45w#trji",
                localDateTime, localDateTime);
        Quote quote = new Quote(1, "Be yourself; everyone else is already taken.", user, null,
                localDateTime, localDateTime);
        Optional<Quote> expected = Optional.of(quote);

        //When
        Optional<Quote> actual = quoteDao.findById(quoteId);
        actual.get().setPostingUser(user);

        //Then
        assertEquals(expected, actual);
    }

    @Test
    void save() {
        //Given
        Quote quoteSave = new Quote("Be yourself; everyone else is already taken.", new User(1));

        //When-Then
        assertDoesNotThrow(() -> quoteDao.save(quoteSave));
    }

    @Test
    void deleteById() {
        //Given
        long quoteId = 1;

        //When-Then
        assertDoesNotThrow(() -> quoteDao.deleteById(quoteId));
    }

    @Test
    void existsById() {
        //Given
        long quoteId = 1;
        boolean expected = true;

        //When
        boolean actual = quoteDao.existsById(quoteId);

        //Then
        assertEquals(expected, actual);
    }
}