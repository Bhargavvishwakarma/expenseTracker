package com.bhargav.expenses.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bhargav.expenses.config.JwtUtil;
import com.bhargav.expenses.dto.LoginDto;
import com.bhargav.expenses.model.User;
import com.bhargav.expenses.repository.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    public User signup(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public LoginDto login(String username,String password){
        LoginDto loginDto = new LoginDto();
        try{    
            User user =  userRepository.findByUsername(username).orElse(null);
            if(user != null && passwordEncoder.matches(password, user.getPassword())){
                loginDto.setJwtToken(jwtUtil.generateToken(username));
                loginDto.setError(false);
                return loginDto;
            }else{
                loginDto.setError(true);
                loginDto.setErrorMsg("Invalid username or password");
            }}catch (Exception e){
                loginDto.setError(true);
                loginDto.setErrorMsg("An error occurred during login " + e.getMessage());
            }
            return loginDto;
    }
}
