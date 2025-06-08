package com.bhargav.expenses.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bhargav.expenses.dto.LoginDto;
import com.bhargav.expenses.model.User;
import com.bhargav.expenses.service.UserService;

@RestController
@RequestMapping("api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<User> signup(@RequestBody User user){
        return ResponseEntity.status(201).body(userService.signup(user));
    }
    @PostMapping("/login")
    public ResponseEntity<LoginDto> login(@RequestBody User user){
        return ResponseEntity.status(201).body(userService.login(user.getUsername(),user.getPassword()));
    }

}
