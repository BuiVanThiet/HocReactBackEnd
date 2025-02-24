package org.example.backendshop.entites;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.backendshop.entites.baseEntity.BaseEntity;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "users")
public class User extends BaseEntity {
//    @Column(name = "first_name")
//    private String  firstName;
//    @Column(name = "last_name")
//    private String lastName;
//    @Column(name = "login")
//    private String login;
//    @Column(name = "password")
//    private String password;

    @Column(name = "code_user", length = 255)
    private String codeUser;

    @Column(name = "full_name", length = 255)
    private String fullName;

    @Column(name = "birth_day")
    private LocalDate birthDay;

    @Column(name = "image", columnDefinition = "NVARCHAR(MAX)")
    private String image;

    @Column(name = "gender")
    private Integer gender;

    @Column(name = "email", length = 255, unique = true)
    private String email;

    @Column(name = "number_phone", length = 255, unique = true)
    private String numberPhone;

    @Column(name = "account", length = 255, unique = true)
    private String account;

    @Column(name = "password", columnDefinition = "NVARCHAR(MAX)")
    private String password;
    @Column(name = "role", length = 255)
    private String role;
}
