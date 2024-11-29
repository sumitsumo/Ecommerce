package com.java.authenticationservice.security;

import com.java.authenticationservice.models.User;
import com.java.authenticationservice.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class CustomUserDetailService implements UserDetailsService
{
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        Optional<User> userOptional=userRepository.findUserByEmail(username);
        if(userOptional.isEmpty())
            throw new UsernameNotFoundException("User not found");

        return new CustomUserDetail(userOptional.get());
    }
}
