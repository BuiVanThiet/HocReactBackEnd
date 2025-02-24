package org.example.backendshop.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@ToString
public class CredentialsDTO {
    private String login;
    private String password;
}
