package com.kameleoon.trialtask.repository.dao;

import com.kameleoon.trialtask.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interface used for interactions with user table.
 *
 * @author Yauheni Tstiov
 */
public interface UserDao extends JpaRepository<User, Long> {
}
