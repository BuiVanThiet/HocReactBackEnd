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
@Table(name = "image_product")
public class ImageProduct extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "id_product")
    private Product product;
    @Column(name = "code_image")
    private String codeImage;
}
