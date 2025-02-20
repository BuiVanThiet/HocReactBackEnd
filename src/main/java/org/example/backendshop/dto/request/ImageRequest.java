package org.example.backendshop.dto.request;

import lombok.*;
import org.example.backendshop.dto.BaseDTO;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ImageRequest extends BaseDTO {
    private String codeImage;
    private Integer idProduct;
}
