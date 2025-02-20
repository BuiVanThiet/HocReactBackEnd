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
@RequestMapping("/weight-type-manage")
public class WeightTypeRestController extends BaseRestController {
    private final SimpMessagingTemplate messagingTemplate;
    @Autowired
    public WeightTypeRestController(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }
    @GetMapping("/all-object")
    public List<Object[]> getAllColorObject() {
        return weightTypeService.getAllWeightType();
    }

    @PostMapping("/add")
    public ResponseEntity<?> getAdd(@RequestBody AttributeRequest attributeRequest) {
        WeightType weightType = new WeightType();
        weightType.setCodeWeightType(attributeRequest.getCode().trim());
        weightType.setNameWeightType(attributeRequest.getName().trim());
        weightType.setStatus(attributeRequest.getStatus());
        weightType.setCreateDate(new Date());
        weightType.setUpdateDate(new Date());
        this.weightTypeService.save(weightType);
        // Gửi thông báo tới tất cả các client về dữ liệu mới
        messagingTemplate.convertAndSend("/topic/weight-type", weightType);
        messagingTemplate.convertAndSend("/topic/product", weightType);
        return ResponseEntity.ok(weightType);
    }
    @PostMapping("/update")
    public ResponseEntity<?> getUpdate(@RequestBody AttributeRequest attributeRequest) {
        WeightType weightType = this.weightTypeService.findById(attributeRequest.getId()).orElse(null);
        weightType.setCodeWeightType(attributeRequest.getCode().trim());
        weightType.setNameWeightType(attributeRequest.getName().trim());
        weightType.setStatus(attributeRequest.getStatus());
        weightType.setCreateDate(new Date());
        weightType.setUpdateDate(new Date());
        this.weightTypeService.save(weightType);
        // Gửi thông báo tới tất cả các client về dữ liệu mới
        messagingTemplate.convertAndSend("/topic/weight-type", weightType);
        messagingTemplate.convertAndSend("/topic/product", weightType);
        return ResponseEntity.ok(weightType);
    }
}
