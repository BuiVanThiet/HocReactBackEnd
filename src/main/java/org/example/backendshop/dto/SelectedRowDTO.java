package org.example.backendshop.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SelectedRowDTO {
    private Integer rowId;
    private Boolean isSelected;
}
