package com.example.securetyStart.Controller;


import com.example.securetyStart.Securety.dto.JwtAuthenticationResponse;
import com.example.securetyStart.Securety.dto.SignInRequest;
import com.example.securetyStart.Securety.dto.SignUpRequest;
import com.example.securetyStart.service.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Tag(name = "Аутентификация")
public class AuthController {

    private final AuthenticationService authenticationService;

    @Operation(summary = "Регистрация пользователя")
    @PostMapping("/signup")
    public JwtAuthenticationResponse signUp(@RequestBody @Valid SignUpRequest request) {
        return authenticationService.signup(request);
    }

    @Operation(summary = "Авторизация пользователя")
    @PostMapping("/signIn")
    public JwtAuthenticationResponse signIn(@RequestBody @Valid SignInRequest request) {
        return authenticationService.sigIn(request);
    }

}
