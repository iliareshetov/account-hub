package com.iliareshetov.accounthub.validator;

import com.iliareshetov.accounthub.entity.User;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class UserValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;

        // Check that the first name is not empty
        if (user.getFirstName() == null || user.getFirstName().isEmpty()) {
            errors.rejectValue("firstName", "error.user", "First name is required.");
        }

        // Check that the last name is not empty
        if (user.getLastName() == null || user.getLastName().isEmpty()) {
            errors.rejectValue("lastName", "error.user", "Last name is required.");
        }

        // Check that the email address is not empty and is valid
        if (user.getEmail() == null || user.getEmail().isEmpty()) {
            errors.rejectValue("email", "error.user", "Email address is required.");
        } else if (!isValidEmail(user.getEmail())) {
            errors.rejectValue("email", "error.user", "Invalid email address.");
        }

        // Check that the birthday is not empty and is valid
        if (user.getBirthday() == null) {
            errors.rejectValue("birthday", "error.user", "Birthday is required.");
        } else if (!isValidDate(user.getBirthday())) {
            errors.rejectValue("birthday", "error.user", "Invalid date format. Use yyyy-mm-dd.");
        }

        // Check that the password is at least 8 characters long
        if (user.getPassword() != null && user.getPassword().length() < 8) {
            errors.rejectValue("password", "error.user", "Password must be at least 8 characters long.");
        }
    }

    private boolean isValidEmail(String email) {
        // Use a regular expression to check if the email address is valid
        String regex = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
        return email.matches(regex);
    }

    private boolean isValidDate(LocalDate date) {
        try {
            return !date.isAfter(LocalDate.now());
        } catch (DateTimeParseException e) {
            return false;
        }
    }

}

