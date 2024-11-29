package com.java.authenticationservice.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponseDTO
{
    RequestStatus requestStatus;
    String token;
}
