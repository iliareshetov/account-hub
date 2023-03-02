package com.iliareshetov.accounthub.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Long id;
    @NotEmpty(message = "First name is required")
    private String firstName;
    @NotEmpty(message = "Last name is required")
    private String lastName;
    @Email(regexp = "^[\\w.+\\-]+@[\\w.\\-]+\\.[\\w]{2,}$", message = "Invalid email format")
    @NotEmpty(message = "Password should not be empty")
    private String email;

    @Size(min = 6, message = "Password must be at least 6 characters long")
    @Pattern(regexp = "^(?=.*[0-9]).*$", message = "Password must contain at least one digit")
    private String password;

    @NotNull(message = "Birthday must not be null")
    @Past(message = "Birthday must be in the past")
    private LocalDate birthday;
}
