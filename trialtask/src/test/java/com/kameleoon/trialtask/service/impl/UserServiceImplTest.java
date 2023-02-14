package com.kameleoon.trialtask.service.impl;

import com.kameleoon.trialtask.entity.User;
import com.kameleoon.trialtask.exception.Error;
import com.kameleoon.trialtask.exception.Notification;
import com.kameleoon.trialtask.exception.ResourceNotFoundedException;
import com.kameleoon.trialtask.exception.ValidationException;
import com.kameleoon.trialtask.repository.dao.UserDao;
import com.kameleoon.trialtask.service.UserService;
import com.kameleoon.trialtask.service.validator.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {
    private UserService userService;
    @Mock
    private UserDao userDao;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private Validator<User> userValidator;

    @BeforeEach
    void setUp() {
        userService = new UserServiceImpl(userDao, passwordEncoder, userValidator);
    }

    @Test
    void save_notValidUser_throwException() {
        //Given
        String name = "Zhenya";
        String password = "Qwerty#123";
        String email = "zhenya@gmail.com";
        User user = new User(name, password, email);
        Notification notification = new Notification();
        notification.addError(new Error());
        Mockito.when(userValidator.validate(user)).thenReturn(notification);

        //When-Then
        assertThrows(ValidationException.class, () -> userService.save(user));
    }

    @Test
    void save_validUser_returnUser() {
        //Given
        String name = "Zhenya";
        String password = "Qwerty#123";
        String encodedPassword = "1535dgdb44n444h4h4df";
        String email = "zhenya@gmail.com";
        User user = new User(name, password, email);
        User daoUser = new User(1, name, encodedPassword);
        Mockito.when(passwordEncoder.encode(password)).thenReturn(encodedPassword);
        Notification notification = new Notification();
        Mockito.when(userValidator.validate(user)).thenReturn(notification);
        Mockito.when(userDao.save(user)).thenReturn(daoUser);
        User expected = new User(1, name, encodedPassword);

        //When
        User actual = userService.save(user);

        //Then
        assertEquals(expected, actual);
    }

    @Test
    void find_notValidId_throwException() {
        //Given
        long userId = 1;
        Mockito.when(userDao.findById(userId)).thenReturn(Optional.empty());

        //When-Then
        assertThrows(ResourceNotFoundedException.class, () -> userService.find(userId));
    }

    @Test
    void find_validId_returnUser() {
        //Given
        long userId = 1;

        User daoUser = new User();
        User expected = new User();
        Mockito.when(userDao.findById(userId)).thenReturn(Optional.of(daoUser));

        //When
        User actual = userService.find(userId);

        //Then
        assertEquals(expected, actual);
    }
}