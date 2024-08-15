package com.example.securetyStart.Securety.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(description = "Sign in request")
public class SignInRequest {


    @NotBlank(message = "Адрес электронной почты не может быть пустыми")
    @Schema(description = "Username", example = "Ali026")
    private String username;

    @NotBlank(message = "Password is required")
    @Schema(description = "Password", example = "My_12345678")
    @Size(max = 255)
    private String password;
}
