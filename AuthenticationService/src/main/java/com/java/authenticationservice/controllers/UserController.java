package com.java.authenticationservice.controllers;

import com.java.authenticationservice.dtos.UserDTO;
import com.java.authenticationservice.models.User;
import com.java.authenticationservice.services.UserService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController
{
    @Autowired
    private UserService userService;

    @GetMapping("/users/{id}")
    public UserDTO getUserDetails(@PathVariable Long id)
    {
        User user=userService.getUserDetails(id);
        UserDTO userDTO=new UserDTO();
        userDTO.setEmail(user.getEmail());
        return userDTO;
    }
}
