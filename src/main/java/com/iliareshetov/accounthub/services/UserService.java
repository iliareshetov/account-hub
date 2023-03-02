package com.iliareshetov.accounthub.services;

import com.iliareshetov.accounthub.dto.UserDto;
import com.iliareshetov.accounthub.entity.User;

import java.util.List;

public interface UserService {

    void saveUser(UserDto userDto);

    User findByEmail(String email);


    List<UserDto> findAllUsers();

    void updateUser(User user);
}
