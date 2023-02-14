package com.kameleoon.trialtask.service.validator;

import com.kameleoon.trialtask.entity.User;
import com.kameleoon.trialtask.exception.Notification;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class UserValidatorTest {
    private Validator<User> userValidator;

    @BeforeEach
    void setUp() {
        userValidator = new UserValidator();
    }

    @Test
    void validate_inValidEmail_notificationWithError() {
        //Given
        User user = new User("zhenya", "Qwerty#123", "zhenya");

        //When
        Notification notification = userValidator.validate(user);

        //Then
        assertTrue(notification.hasErrors());
    }

    @Test
    void validate_nullEmail_notificationWithError() {
        //Given
        User user = new User("zhenya", "Qwerty#123", null);

        //When
        Notification notification = userValidator.validate(user);

        //Then
        assertTrue(notification.hasErrors());
    }

    @Test
    void validate_bigName_notificationWithError() {
        //Given
        User user = new User("zhenya3g422j23kj63k3k46k43k3k363", "Qwerty#123", "zhenya@gmail.com");

        //When
        Notification notification = userValidator.validate(user);

        //Then
        assertTrue(notification.hasErrors());
    }

    @Test
    void validate_littleName_notificationWithError() {
        //Given
        User user = new User("z", "Qwerty#123", "zhenya@gmail.com");

        //When
        Notification notification = userValidator.validate(user);

        //Then
        assertTrue(notification.hasErrors());
    }

    @Test
    void validate_nullName_notificationWithError() {
        //Given
        User user = new User(null, "Qwerty#123", "zhenya@gmail.com");

        //When
        Notification notification = userValidator.validate(user);

        //Then
        assertTrue(notification.hasErrors());
    }

    @Test
    void validate_validUser_notificationWithoutError() {
        //Given
        User user = new User("zhenya", "Qwerty#123", "zhenya@gmail.com");

        //When
        Notification notification = userValidator.validate(user);

        //Then
        assertFalse(notification.hasErrors());
    }
}