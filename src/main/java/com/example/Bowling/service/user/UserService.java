package com.example.Bowling.service.user;

import com.example.Bowling.domain.dto.UserDTO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface UserService {

    void saveUser(UserDTO user);

    void signinMethod(UserDTO user, HttpServletResponse response);

    UserDTO getUser(HttpServletRequest request);

    UserDTO updateInfo(UserDTO user);

    void deleteUser(HttpServletRequest request);

    String getId(HttpServletRequest request);

}
