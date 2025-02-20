package org.example.backendshop.restController;

import org.example.backendshop.dto.request.AttributeRequest;
import org.example.backendshop.entites.*;
import org.example.backendshop.restController.base.BaseRestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000/")  // URL của React app
@RequestMapping("/origin-manage")
public class OriginRestController extends BaseRestController {
    private final SimpMessagingTemplate messagingTemplate;
    @Autowired
    public OriginRestController(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }
    @GetMapping("/all-object")
    public List<Object[]> getAllColorObject() {
        return originService.getAllOrigin();
    }

    @PostMapping("/add")
    public ResponseEntity<?> getAdd(@RequestBody AttributeRequest attributeRequest) {
        Origin origin = new Origin();
        origin.setCodeOrigin(attributeRequest.getCode().trim());
        origin.setNameOrigin(attributeRequest.getName().trim());
        origin.setStatus(attributeRequest.getStatus());
        origin.setCreateDate(new Date());
        origin.setUpdateDate(new Date());
        this.originService.save(origin);
        // Gửi thông báo tới tất cả các client về dữ liệu mới
        messagingTemplate.convertAndSend("/topic/origin", origin);
        messagingTemplate.convertAndSend("/topic/product", origin);
        return ResponseEntity.ok(origin);
    }
    @PostMapping("/update")
    public ResponseEntity<?> getUpdate(@RequestBody AttributeRequest attributeRequest) {
        Origin origin = this.originService.findById(attributeRequest.getId()).orElse(null);
        origin.setCodeOrigin(attributeRequest.getCode().trim());
        origin.setNameOrigin(attributeRequest.getName().trim());
        origin.setStatus(attributeRequest.getStatus());
        origin.setCreateDate(new Date());
        origin.setUpdateDate(new Date());
        this.originService.save(origin);
        // Gửi thông báo tới tất cả các client về dữ liệu mới
        messagingTemplate.convertAndSend("/topic/origin", origin);
        messagingTemplate.convertAndSend("/topic/product", origin);
        return ResponseEntity.ok(origin);
    }
}
