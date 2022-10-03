package com.example.Bowling.controller;

import com.example.Bowling.domain.dto.UserDTO;
import com.example.Bowling.service.user.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserServiceImpl userService;

    @PostMapping(value = "/user")
    public ResponseEntity signup (@RequestBody UserDTO user){
        userService.saveUser(user);
        return ResponseEntity.created(URI.create("/user")).body("Created User");
    }

    @PostMapping(value = "/user/signin")
    public ResponseEntity signin (@RequestBody UserDTO user){

        try{

        } catch (Exception e){

        }

        return ResponseEntity.ok().body("User Certified");
    }

    @GetMapping(value = "/user")
    public ResponseEntity userInfo (HttpServletRequest request){
        userService.getUser(request);
        return ResponseEntity.ok().body("");
    }

    @PutMapping(value = "/user")
    public ResponseEntity updateInfo (UserDTO user){
        return ResponseEntity.ok().body(userService.updateInfo(user));
    }

    @DeleteMapping(value = "/user")
    public ResponseEntity deleteUser (HttpServletRequest request) {
        userService.deleteUser(request);
        return ResponseEntity.ok().body("Delete Success");
    }
}
