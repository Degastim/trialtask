package com.kameleoon.trialtask.service.impl;

import com.kameleoon.trialtask.entity.Vote;
import com.kameleoon.trialtask.exception.ResourceNotFoundedException;
import com.kameleoon.trialtask.repository.dao.UserDao;
import com.kameleoon.trialtask.repository.dao.VoteDao;
import com.kameleoon.trialtask.service.VoteService;
import com.kameleoon.trialtask.service.validator.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class VoteServiceImplTest {
    private VoteService voteService;
    @Mock
    private VoteDao voteDao;
    @Mock
    private UserDao userDao;
    @Mock
    private Validator<Vote> voteValidator;

    @BeforeEach
    void setUp() {
        voteService = new VoteServiceImpl(voteDao, userDao, voteValidator);
    }

    @Test
    void find_notValidId_throwException() {
        long voteId = 1;
        Mockito.when(voteDao.findById(voteId)).thenReturn(Optional.empty());

        //When-Then
        assertThrows(ResourceNotFoundedException.class, () -> voteService.find(voteId));
    }

    @Test
    void find_validId_returnVote() {
        long voteId = 1;
        Vote vote = new Vote();
        Vote expected = new Vote();
        Mockito.when(voteDao.findById(voteId)).thenReturn(Optional.of(vote));

        //When
        Vote actual = voteService.find(voteId);

        //Then
        assertEquals(expected, actual);
    }
}