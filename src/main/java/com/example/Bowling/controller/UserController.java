package com.example.Bowling.controller;

import com.example.Bowling.domain.dto.UserDTO;
import com.example.Bowling.service.user.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URI;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserServiceImpl userService;

    Cookie cookie = new Cookie("Cookie","forSecure");

    @PostMapping(value = "/user")
    public ResponseEntity signup (@RequestBody UserDTO user){
        userService.saveUser(user);
        return ResponseEntity.created(URI.create("/user")).body("Created User");
    }

    @PostMapping(value = "/user/signin")
    public ResponseEntity signin (@RequestBody UserDTO user, HttpServletResponse response){

        userService.signinMethod(user, response);

        cookie.setMaxAge(7*24*60*60);
        cookie.setHttpOnly(true); //token 쿠키 저장 방식의 csrf 취약 문제 방지 위해  httponly true 설정
        cookie.setSecure(true); //security : true
        cookie.setPath("/");

        response.addCookie(cookie);

        return ResponseEntity.ok().body("SignIn Success");
    }

    @GetMapping(value = "/user")
    public ResponseEntity userInfo (HttpServletRequest request) {
        return ResponseEntity.ok().body(userService.getUser(request));
    }

    @PutMapping(value = "/user")
    public ResponseEntity updateInfo (UserDTO user) {
        return ResponseEntity.ok().body(userService.updateInfo(user));
    }

    @DeleteMapping(value = "/user")
    public ResponseEntity deleteUser (HttpServletRequest request) {
        userService.deleteUser(request);
        return ResponseEntity.ok().body("Delete Success");
    }
}
