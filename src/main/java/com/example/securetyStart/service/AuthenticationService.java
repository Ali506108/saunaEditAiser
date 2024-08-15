package com.example.securetyStart.service;

import com.example.securetyStart.Securety.dto.JwtAuthenticationResponse;
import com.example.securetyStart.Securety.dto.SignInRequest;
import com.example.securetyStart.Securety.dto.SignUpRequest;
import com.example.securetyStart.Securety.service.JwtService;
import com.example.securetyStart.model.Role;
import com.example.securetyStart.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserService userService;
    private final JwtService jwtService;
    private final PasswordEncoder pEcncoder;
    private final AuthenticationManager authenticationManager;

    public JwtAuthenticationResponse signup(SignUpRequest request) {
        // Ensure you include the email field when creating the User object
        var userAuth = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())  // Add this line
                .password(pEcncoder.encode(request.getPassword()))
                .role(Role.ROLE_USER)
                .build();

        userService.create(userAuth);

        var jwt = jwtService.generateToken(userAuth);
        return new JwtAuthenticationResponse(jwt);
    }

    public JwtAuthenticationResponse sigIn(SignInRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getUsername(),
                request.getPassword()
        ));

        var user = userService
                .userDetailsService()
                .loadUserByUsername(request.getUsername());

        var jwt = jwtService.generateToken(user);
        return new JwtAuthenticationResponse(jwt);
    }
}
