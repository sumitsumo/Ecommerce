package com.java.authenticationservice.controllers;


import com.java.authenticationservice.dtos.*;
import com.java.authenticationservice.exceptions.SessionExpiredException;
import com.java.authenticationservice.exceptions.SessionNotFoundException;
import com.java.authenticationservice.services.AuthService;
import org.antlr.v4.runtime.misc.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController
{
    @Autowired
    private AuthService authService;

    @PostMapping("/sign_up")
    public ResponseEntity<SignUpResponseDTO> signUp(@RequestBody SignUpRequestDTO signUpRequestDTO)
    {
        SignUpResponseDTO response=new SignUpResponseDTO();
        try{
            if(authService.signUp(signUpRequestDTO.getEmail(),signUpRequestDTO.getPassword()))
                response.setRequestStatus(RequestStatus.Success);
            else
                response.setRequestStatus(RequestStatus.Failure);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        catch (Exception e)
        {
            response.setRequestStatus(RequestStatus.Failure);
            return new ResponseEntity<>(response, HttpStatus.CONFLICT);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO loginRequestDTO)
    {
        LoginResponseDTO response=new LoginResponseDTO();
        try{
            Pair<Boolean,String> token = authService.login(loginRequestDTO.getEmail(),loginRequestDTO.getPassword());
            response.setRequestStatus(RequestStatus.Success);
            response.setToken(String.valueOf(token));
            MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
            headers.add(HttpHeaders.SET_COOKIE,token.b);
            return new ResponseEntity<>(response, headers, HttpStatus.OK);
        }
        catch (Exception e)
        {
            response.setRequestStatus(RequestStatus.Failure);
            return new ResponseEntity<>(response, HttpStatus.CONFLICT);
        }
    }

    @PostMapping("/validateToken")
    public boolean validateToken(@RequestBody ValidateTokenDTO validateTokenDTO) {
        return authService.validateToken(
                validateTokenDTO.getUserId(),validateTokenDTO.getToken());
    }
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleRuntimeException(RuntimeException ex) {
        // Return just the error message
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
