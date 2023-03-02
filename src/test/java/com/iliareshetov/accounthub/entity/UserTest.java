package com.iliareshetov.accounthub.entity;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserTest {

    @Test
    public void testUserFields() {
        User user = new User();

        Long id = 1L;
        String firstName = "John";
        String lastName = "Doe";
        String email = "johndoe@example.com";
        String password = "password";
        LocalDate birthday = LocalDate.of(1990, 1, 1);

        Role userRole = new Role(null, "USER", null);
        Role adminRole = new Role(null, "ADMIN", null);
        List<Role> roles = Arrays.asList(userRole, adminRole);

        user.setId(id);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setPassword(password);
        user.setBirthday(birthday);
        user.setRoles(roles);

        assertEquals(id, user.getId());
        assertEquals(firstName, user.getFirstName());
        assertEquals(lastName, user.getLastName());
        assertEquals(email, user.getEmail());
        assertEquals(password, user.getPassword());
        assertEquals(birthday, user.getBirthday());
        assertEquals(roles, user.getRoles());

        assertEquals(userRole.getName(), roles.get(0).getName());
        assertEquals(adminRole.getName(), roles.get(1).getName());
    }

    @Test
    public void testRoleFields() {
        List<User> users = Arrays.asList(new User(), new User());

        Role role = new Role(1L, "USER", users);

        assertEquals(1L, role.getId());
        assertEquals("USER", role.getName());
        assertEquals(users, role.getUsers());
    }
}
