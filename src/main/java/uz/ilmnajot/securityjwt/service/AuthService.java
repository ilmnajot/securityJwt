package uz.ilmnajot.securityjwt.service;

import uz.ilmnajot.securityjwt.dto.AuthResponse;
import uz.ilmnajot.securityjwt.dto.SignInRequest;
import uz.ilmnajot.securityjwt.dto.SignUpRequest;
import uz.ilmnajot.securityjwt.entity.User;

public interface AuthService {

    User signUp(SignUpRequest request);

    AuthResponse signIn(SignInRequest request);
}
