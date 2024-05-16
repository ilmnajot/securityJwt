package uz.ilmnajot.securityjwt.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.ilmnajot.securityjwt.dto.AuthResponse;
import uz.ilmnajot.securityjwt.dto.SignInRequest;
import uz.ilmnajot.securityjwt.dto.SignUpRequest;
import uz.ilmnajot.securityjwt.entity.User;
import uz.ilmnajot.securityjwt.service.AuthService;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signUp")
    public ResponseEntity<?> signUp(@RequestBody SignUpRequest signUpRequest) {
        User user = authService.signUp(signUpRequest);
        return ResponseEntity.ok(user);
    }
    @PostMapping("/signIn")
    public ResponseEntity<?> signIn(@RequestBody SignInRequest signInRequest) {
        AuthResponse authResponse = authService.signIn(signInRequest);
        return ResponseEntity.ok(authResponse);
    }
}
