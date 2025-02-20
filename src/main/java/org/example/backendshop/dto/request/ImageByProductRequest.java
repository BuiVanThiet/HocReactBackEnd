package org.example.backendshop.dto.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ImageByProductRequest {
    private String uid;
    private String name;
    private String status;
    private String url;

    public ImageByProductRequest(Integer uid, String name, String status, String url) {
        this.uid = String.valueOf(uid);
        this.name = name;
        this.status = status;
        this.url = url;
    }
}
