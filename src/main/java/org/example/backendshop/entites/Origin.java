package org.example.backendshop.entites;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.backendshop.entites.baseEntity.BaseEntity;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "origin")
public class Origin extends BaseEntity {
    @Column(name = "code_origin")
    private String codeOrigin;
    @Column(name = "name_origin")
    private String nameOrigin;

}
