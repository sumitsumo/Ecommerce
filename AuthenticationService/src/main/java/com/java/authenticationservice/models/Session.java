package com.java.authenticationservice.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Session extends BaseModel
{
    SessionState sessionState;
    String token;
    @ManyToOne
    User user;
}
