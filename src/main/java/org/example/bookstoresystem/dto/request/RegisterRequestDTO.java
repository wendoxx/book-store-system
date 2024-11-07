package org.example.bookstoresystem.dto.request;

import lombok.Data;
import org.example.bookstoresystem.role.UserRole;

@Data
public class RegisterRequestDTO {
    private String username;
    private String password;
    private UserRole role;
}
