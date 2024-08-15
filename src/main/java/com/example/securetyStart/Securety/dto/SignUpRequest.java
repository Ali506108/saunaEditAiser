package com.example.securetyStart.Securety.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(description = "Sign up request")
public class SignUpRequest {


    @Schema(description = "username", example = "Ali026")
    @Size(min = 3, max = 20)
    @NotBlank(message = "Username is required")
    private String username;

    @Schema(description = "email", example = "aliuisen772@gmail.com")
    @Email(message = "Invalid email format")
    @NotBlank(message = "Адрес электронной почты не может быть пустыми")
    @Size(min = 10 , max = 40)
    private String email;

    @Schema(description = "password", example = "My_12345678")
    @Size(max = 255)
    @NotBlank(message = "Password is required")
    private String password;


}
