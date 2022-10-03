package com.example.Bowling.service.user;

import com.example.Bowling.domain.dto.UserDTO;

import javax.servlet.http.HttpServletRequest;

public interface UserService {

    void saveUser(UserDTO user);

    UserDTO getUser(HttpServletRequest request);

    UserDTO updateInfo(UserDTO user);

    void deleteUser(HttpServletRequest request);

    UserDTO getId(HttpServletRequest request);

}
