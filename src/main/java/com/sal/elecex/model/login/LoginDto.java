package com.sal.elecex.model.login;

import java.time.LocalDateTime;

import jakarta.validation.constraints.Email;
import lombok.Data;

@Data
public class LoginDto {

    private Integer userId;
    private String name;
    private String lastName;
    private String username;
    private String password;
    private String email;
    @Email(message = "el email no es correcto")
    private LocalDateTime creationDate = LocalDateTime.now();
    private boolean isUp = true;
}
