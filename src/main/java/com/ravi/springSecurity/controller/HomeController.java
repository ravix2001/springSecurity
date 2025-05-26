package com.ravi.springSecurity.controller;

import com.ravi.springSecurity.entity.User;
import com.ravi.springSecurity.service.UserDetailsServiceImpl;
import com.ravi.springSecurity.service.UserService;
import com.ravi.springSecurity.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @GetMapping
    public String greet() {
        return "Hello, user";
    }

    @PostMapping("/signup")
    public User createUser(@RequestBody User user) {
        userService.saveNewUser(user);
        return user;
    }

    @PostMapping("/login")
    public String login(@RequestBody User user) {
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
            UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUsername());
            String jwtToken = jwtUtil.generateToken(userDetails.getUsername());
            return jwtToken;
        }catch (Exception e){
            return "Invalid Credentials";
        }
    }
}
