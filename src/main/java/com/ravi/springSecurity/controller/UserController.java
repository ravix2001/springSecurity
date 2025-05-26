package com.ravi.springSecurity.controller;

import com.ravi.springSecurity.entity.User;
import com.ravi.springSecurity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class UserController {

    @Autowired
    UserService userService;

//    @GetMapping("/csrf-token")
//    public CsrfToken getCSRFToken(HttpServletRequest request) {
//        return (CsrfToken) request.getAttribute("_csrf");
//    }

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userService.findAll();
    }

    @GetMapping("/user")
    public User getByUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User userInDb = userService.findByUsername(username);
        return userService.findByUsername(username);
    }


}
