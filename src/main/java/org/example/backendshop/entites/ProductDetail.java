package org.example.backendshop.entites;

import jakarta.persistence.*;
import lombok.*;
import org.example.backendshop.entites.baseEntity.BaseEntity;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "product_detail")
@ToString
public class ProductDetail extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "id_product")
    private Product product;
    @ManyToOne
    @JoinColumn(name = "id_color")
    private Color color;
    @ManyToOne
    @JoinColumn(name = "id_size")
    private Size size;
    @ManyToOne
    @JoinColumn(name = "id_weight_type")
    private WeightType weightType;
    @Column(name = "selling_price")
    private BigDecimal sellingPrice;
    @Column(name = "import_price")
    private BigDecimal importPrice;
    @Column(name = "quantity")
    private Integer quantity;
    @Column(name = "weight")
    private BigDecimal weight;

}
