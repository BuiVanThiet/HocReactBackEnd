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
@Table(name = "color")
public class Color extends BaseEntity {
    @Column(name = "code_color")
    private String codeColor;
    @Column(name = "name_color")
    private String nameColor;

}
