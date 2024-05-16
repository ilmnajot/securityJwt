package uz.ilmnajot.securityjwt.service;

import org.springframework.security.core.userdetails.UserDetails;

import java.util.Map;

public interface JWTService {

    String generateToken(UserDetails userDetails);

    String extractUsername(String token);

    public boolean isTokenExpired(String token);

    public boolean isTokenValid(String token, UserDetails userDetails);


    String generateRefreshToken(Map<String, Object> extractClaims, UserDetails userDetails);
}
