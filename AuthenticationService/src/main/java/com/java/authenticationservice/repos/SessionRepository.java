package com.java.authenticationservice.repos;

import com.java.authenticationservice.models.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SessionRepository extends JpaRepository<Session, Long>
{
    Optional<Session> findByTokenAndUser_Id(String token, Long userId);
}
