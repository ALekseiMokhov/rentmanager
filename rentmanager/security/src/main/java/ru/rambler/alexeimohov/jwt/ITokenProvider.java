package ru.rambler.alexeimohov.jwt;

import org.springframework.security.core.Authentication;


public interface ITokenProvider {
    String generateToken(Authentication authentication);

    Long getUserIdFromJWT(String token);

    boolean validateToken(String token);
}
