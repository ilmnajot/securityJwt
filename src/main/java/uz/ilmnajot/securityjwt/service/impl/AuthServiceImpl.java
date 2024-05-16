package uz.ilmnajot.securityjwt.service.impl;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.ilmnajot.securityjwt.dto.AuthResponse;
import uz.ilmnajot.securityjwt.dto.SignInRequest;
import uz.ilmnajot.securityjwt.dto.SignUpRequest;
import uz.ilmnajot.securityjwt.entity.Role;
import uz.ilmnajot.securityjwt.entity.User;
import uz.ilmnajot.securityjwt.repository.UserRepository;
import uz.ilmnajot.securityjwt.service.AuthService;
import uz.ilmnajot.securityjwt.service.JWTService;

import java.util.Collections;
import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    private final JWTService jwtService;

    public User signUp(SignUpRequest request){
        User user = new User();
        user.setFirstName(request.getFirstName());
        user.setSecondName(request.getLastName());
        user.setRole(Role.USER);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        return userRepository.save(user);
    }

    public AuthResponse signIn(SignInRequest request){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getEmail(),
                request.getPassword()
        ));

        var user = userRepository.findByEmail(request.getEmail()).orElseThrow(() -> new IllegalArgumentException("Invalid email"));
        var token = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(new HashMap<>(), user);

        AuthResponse authResponse = new AuthResponse();
        authResponse.setToken(token);
        authResponse.setRefreshToken(refreshToken);
        return authResponse;
    }
}
