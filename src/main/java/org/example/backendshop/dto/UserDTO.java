package org.example.backendshop.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class UserDTO extends BaseDTO{
    private String codeUser;
    private String fullName;
    private LocalDate birthDay;
    private String image;
    private Integer gender;
    private String email;
    private String numberPhone;
    private String account;
    private String role;
    private String token;
}
