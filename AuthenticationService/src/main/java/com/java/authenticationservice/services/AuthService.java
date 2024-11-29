package com.java.authenticationservice.services;

import com.java.authenticationservice.exceptions.*;
import com.java.authenticationservice.models.Session;
import com.java.authenticationservice.models.SessionState;
import com.java.authenticationservice.models.User;
import com.java.authenticationservice.repos.SessionRepository;
import com.java.authenticationservice.repos.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.MacAlgorithm;
import org.antlr.v4.runtime.misc.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.token.TokenService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class AuthService
{
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private SessionRepository sessionRepository;
    @Autowired
    private SecretKey secretKey;

    public boolean signUp(String email, String password)
            throws UserAlreadyExistsException
    {
        if(userRepository.findUserByEmail(email).isPresent())
            throw new UserAlreadyExistsException
                    ("User with email "+ email+" already exists,please LOGIN");
        User user = new User();
        String encodedPassword=passwordEncoder.encode(password);
        user.setEmail(email);
        user.setPassword(encodedPassword);
        userRepository.save(user);
        return true;
    }

    public Pair<Boolean,String> login(String email, String password)
            throws UserNotFoundException, WrongPasswordException
    {
        Optional<User> userOptional = userRepository.findUserByEmail(email);
        if(!userOptional.isPresent())
            throw new UserNotFoundException
                    ("User with email "+ email+" doesnt exist,please SIGNUP");
        boolean matches = passwordEncoder.matches(password,userOptional.get().getPassword());
        if(!matches)
            throw new WrongPasswordException("Wrong password");


//        byte[] payloadBytes = payload.getBytes(StandardCharsets.UTF_8);
        Map<String, Object> claims = new HashMap<>();
        long currTimestamp = System.currentTimeMillis();
        claims.put("issued_at", currTimestamp);
        claims.put("expires_at", currTimestamp + 864000L);
        claims.put("id", userOptional.get().getId());
        claims.put("issuer","Sumit");
        String token = Jwts.builder().claims(claims).signWith(secretKey).compact();

        //save the token in db
        Session userSession = new Session();
        userSession.setToken(token);
        userSession.setUser(userOptional.get());
        userSession.setSessionState(SessionState.ACTIVE);
        sessionRepository.save(userSession);

        return new Pair<Boolean,String>(true,token);
    }

    public boolean validateToken(Long userId,String token)  {
        Optional<Session> sessionOptional = sessionRepository.findByTokenAndUser_Id(token, userId);
        if(sessionOptional.isEmpty()){
            throw new SessionNotFoundException("Session not found");
//            return false;
        }

        JwtParser jwtParser = Jwts.parser().verifyWith(secretKey).build();
        Claims claims = jwtParser.parseSignedClaims(token).getPayload();
        Long expiresAt =(Long)claims.get("expires_at");
        Long currTimestamp = (Long)System.currentTimeMillis();
        if(currTimestamp > expiresAt)
        {
            //mark the status and save
            sessionOptional.get().setSessionState(SessionState.EXPIRED);
            sessionRepository.save(sessionOptional.get());
            throw new SessionExpiredException("Session has expired");
//            return false;
        }
        return true;
    }
}
