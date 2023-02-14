package com.kameleoon.trialtask.service;

import com.kameleoon.trialtask.entity.User;

/**
 * Quote service for users
 *
 * @author Yauheni Tstiov
 */
public interface UserService {
    User save(User user);

    User find(long userId);
}
