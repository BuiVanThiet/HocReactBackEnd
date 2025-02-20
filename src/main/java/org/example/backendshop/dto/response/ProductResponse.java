package org.example.backendshop.dto.response;

import lombok.*;
import org.example.backendshop.dto.BaseDTO;

import java.util.Date;

@Getter
@Setter
//@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProductResponse extends BaseDTO {
    private String codeProduct;
    private String nameProduct;
    private Integer idCategory;
    private Integer idManufacturer;
    private Integer idOrigin;
    private String describe;

    public ProductResponse(
            Integer id, Date createDate, Date updateDate, Integer status,
            String codeProduct, String nameProduct, Integer idCategory, Integer idManufacturer, Integer idOrigin, String describe) {
        super(id, createDate, updateDate, status);
        this.codeProduct = codeProduct;
        this.nameProduct = nameProduct;
        this.idCategory = idCategory;
        this.idManufacturer = idManufacturer;
        this.idOrigin = idOrigin;
        this.describe = describe;
    }

}
