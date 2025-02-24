package org.example.backendshop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserResponseDTO {
    private String username;
    private Collection<? extends GrantedAuthority> authorities;

}
