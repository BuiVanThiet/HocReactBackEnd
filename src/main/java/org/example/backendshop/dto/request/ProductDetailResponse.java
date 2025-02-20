package org.example.backendshop.dto.request;

import lombok.*;
import org.example.backendshop.dto.BaseDTO;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProductDetailResponse extends BaseDTO {
    private String idProduct;
    private String idColor;
    private String idSize;
    private String quantity;
    private String importPrice;
    private String sellingPrice;
    private String weight;
    private String idWeight;

}
