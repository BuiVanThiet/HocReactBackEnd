package org.example.backendshop.dto.request;

import lombok.*;
import org.example.backendshop.dto.BaseDTO;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AttributeRequest extends BaseDTO {
    private String code;
    private String name;
}
