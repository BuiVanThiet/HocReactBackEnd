package org.example.backendshop.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class SigUpDTO {
    private String codeUser;
    private String fullName;
    private LocalDate birthDay;
    private String image;
    private Integer gender;
    private String email;
    private String numberPhone;
    private String account;
    private String password;
    private String role;
}
