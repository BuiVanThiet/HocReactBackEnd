package org.example.backendshop.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class WebSocketController {
    private final SimpMessagingTemplate messagingTemplate;

    public WebSocketController(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    // Gửi thông báo đến client khi có thay đổi
    @MessageMapping("/attribute/update")
    public void sendAttributeUpdate(String message) {
        messagingTemplate.convertAndSend("/topic/attribute", message); // Gửi dữ liệu đến /topic/color
    }
    @MessageMapping("/product/update")
    public void sendProductUpdate(String message) {
        messagingTemplate.convertAndSend("/topic/product", message); // Gửi dữ liệu đến /topic/color
    }
    @MessageMapping("/color/update")
    public void sendColorUpdate(String message) {
        messagingTemplate.convertAndSend("/topic/color", message); // Gửi dữ liệu đến /topic/color
    }
    @MessageMapping("/category/update")
    public void sendCategoryUpdate(String message) {
        messagingTemplate.convertAndSend("/topic/category", message); // Gửi dữ liệu đến /topic/color
    }
    @MessageMapping("/manufacturer/update")
    public void sendManufacturerUpdate(String message) {
        messagingTemplate.convertAndSend("/topic/manufacturer", message); // Gửi dữ liệu đến /topic/color
    }
    @MessageMapping("/origin/update")
    public void sendOriginUpdate(String message) {
        messagingTemplate.convertAndSend("/topic/origin", message); // Gửi dữ liệu đến /topic/color
    }
    @MessageMapping("/size/update")
    public void sendSizeUpdate(String message) {
        messagingTemplate.convertAndSend("/topic/size", message); // Gửi dữ liệu đến /topic/color
    }
    @MessageMapping("/weight-type/update")
    public void sendWeightTypeUpdate(String message) {
        messagingTemplate.convertAndSend("/topic/weight-type", message); // Gửi dữ liệu đến /topic/color
    }
    @MessageMapping("/product-detail/update")
    public void sendProductDetailUpdate(String message) {
        messagingTemplate.convertAndSend("/topic/product-detail", message); // Gửi dữ liệu đến /topic/color
    }

}
