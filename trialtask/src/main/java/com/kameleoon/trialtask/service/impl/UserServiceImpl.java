package com.kameleoon.trialtask.service.impl;

import com.kameleoon.trialtask.entity.User;
import com.kameleoon.trialtask.exception.Error;
import com.kameleoon.trialtask.exception.Notification;
import com.kameleoon.trialtask.exception.ResourceNotFoundedException;
import com.kameleoon.trialtask.exception.ValidationException;
import com.kameleoon.trialtask.repository.dao.UserDao;
import com.kameleoon.trialtask.service.UserService;
import com.kameleoon.trialtask.service.validator.Validator;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserDao userDao;
    private final PasswordEncoder passwordEncoder;
    private final Validator<User> userValidator;

    public UserServiceImpl(UserDao userDao, PasswordEncoder passwordEncoder, Validator<User> userValidator) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
        this.userValidator = userValidator;
    }

    @Override
    public User save(User user) {
        Notification notification = userValidator.validate(user);
        if (notification.hasErrors()) {
            throw new ValidationException(notification);
        }
        String password = user.getPassword();
        String encodedPassword = passwordEncoder.encode(password);
        user.setPassword(encodedPassword);
        return userDao.save(user);
    }

    @Override
    public User find(long userId) {
        Optional<User> optionalUser = userDao.findById(userId);
        if (optionalUser.isEmpty()) {
            Notification notification = new Notification();
            notification.addError(new Error("user-02", "User with this ID not found",
                    "User with this ID not found"));
            throw new ResourceNotFoundedException(notification);
        }
        return optionalUser.get();
    }
}