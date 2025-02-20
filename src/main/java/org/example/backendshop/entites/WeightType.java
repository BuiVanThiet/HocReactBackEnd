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
@Table(name = "weight_type")
public class WeightType extends BaseEntity {
    @Column(name = "code_weight_type")
    private String codeWeightType;
    @Column(name = "name_weight_type")
    private String nameWeightType;

}
