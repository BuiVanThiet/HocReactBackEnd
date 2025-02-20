package org.example.backendshop.entites;

import jakarta.persistence.*;
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
@Table(name = "product")
public class Product extends BaseEntity {
    @Column(name = "code_product")
    private String codeProduct;
    @Column(name = "name_product")
    private String nameProduct;
    @ManyToOne
    @JoinColumn(name = "id_manufacturer")
    private Manufacturer manufacturer;
    @ManyToOne
    @JoinColumn(name = "id_origin")
    private Origin origin;
    @ManyToOne
    @JoinColumn(name = "id_category")
    private Category category;
    @Column(name = "describe")
    private String describe;

}
