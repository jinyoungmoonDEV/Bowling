package com.example.Bowling.service.token;

import com.example.Bowling.domain.dto.UserDTO;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
public interface TokenService {
    void loginMethod(UserDTO user, HttpServletResponse response); //유저 정보 확인, 토큰 발급
    UserDTO decodeJWT(HttpServletRequest request); //JWT Decode
    void createToken(UserDTO user, HttpServletResponse response);
}
