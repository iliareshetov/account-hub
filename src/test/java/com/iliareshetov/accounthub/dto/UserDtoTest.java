package com.iliareshetov.accounthub.dto;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.time.LocalDate;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringJUnitConfig
@SpringBootTest
public class UserDtoTest {

    private final ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
    private final Validator validator = validatorFactory.getValidator();

    @Test
    public void testValidUserDto() {
        UserDto userDto = new UserDto(
                1L,
                "John",
                "Doe",
                "johndoe@example.com",
                "p4$$w0rd",
                LocalDate.of(1990, 1, 1)
        );

        assertTrue(validator.validate(userDto).isEmpty());
    }

    @Test
    public void testInvalidUserDto() {
        UserDto userDto = new UserDto(
                null,
                "",
                "",
                "notanemail",
                "password",
                LocalDate.now()
        );

        assertFalse(validator.validate(userDto).isEmpty());
    }

    @Test
    public void testInvalidPassword() {
        UserDto userDto = new UserDto(
                1L,
                "John",
                "Doe",
                "johndoe@example.com",
                "password",
                LocalDate.of(1990, 1, 1)
        );

        assertFalse(validator.validateProperty(userDto, "password").isEmpty());
    }

    @Test
    void testValidPassword() {
        UserDto userDto = new UserDto(
                null,
                "Jane",
                "Doe",
                "janedoe@example.com",
                "password1",
                LocalDate.of(1995, 6, 15)
        );

        Set<ConstraintViolation<UserDto>> violations = validator.validate(userDto);

        assertEquals(0, violations.size());
    }

    @Test
    void testShortPassword() {
        UserDto userDto = new UserDto(
                null,
                "John",
                "Doe",
                "johndoe@example.com",
                "12345",
                LocalDate.of(1990, 1, 1)
        );

        Set<ConstraintViolation<UserDto>> violations = validator.validate(userDto);

        assertEquals(1, violations.size());

        for (ConstraintViolation<UserDto> violation : violations) {
            if (violation.getPropertyPath().toString().equals("password")) {
                assertTrue(violation.getMessage().contains("Password must be at least 6 characters long"));
            }
        }
    }
    @Test
    void testNoDigitsInPassword() {
        UserDto userDto = new UserDto(
                null,
                "John",
                "Doe",
                "johndoe@example.com",
                "password",
                LocalDate.of(1990, 1, 1)
        );

        Set<ConstraintViolation<UserDto>> violations = validator.validate(userDto);

        assertEquals(1, violations.size());

        for (ConstraintViolation<UserDto> violation : violations) {
            if (violation.getPropertyPath().toString().equals("password")) {
                assertTrue(violation.getMessage().contains("Password must contain at least one digit"));
            }
        }
    }

}
