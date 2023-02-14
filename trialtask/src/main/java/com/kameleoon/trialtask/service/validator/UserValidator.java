package com.kameleoon.trialtask.service.validator;

import com.kameleoon.trialtask.entity.User;
import com.kameleoon.trialtask.exception.Error;
import com.kameleoon.trialtask.exception.Notification;
import org.springframework.stereotype.Component;

@Component
public class UserValidator implements Validator<User> {
    private final String regex = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$";

    @Override
    public Notification validate(User user) {
        Notification notification = new Notification();
        String email = user.getEmail();
        String name = user.getName();
        if (email == null || !email.matches(regex)) {
            notification.addError(new Error("user-01", "Not valid email", "Not valid email"));
        }
        if (name == null || name.length() > 10 || name.length() < 3) {
            notification.addError(new Error("user-01", "Not valid name", "Not valid name"));
        }
        return notification;
    }
}
