package com.java.authenticationservice.services;


import com.java.authenticationservice.models.User;
import com.java.authenticationservice.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService
{
    @Autowired
    private UserRepository userRepository;

    public User getUserDetails(Long id)
    {
        return userRepository.findById(id).get();
    }
}
