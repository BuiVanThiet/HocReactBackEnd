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
@RequestMapping("/manufacturer-manage")
public class ManufacturerRestController extends BaseRestController {
    private final SimpMessagingTemplate messagingTemplate;
    @Autowired
    public ManufacturerRestController(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }
    @GetMapping("/all-object")
    public List<Object[]> getAllColorObject() {
        return manufacturerService.getAllManufacturer();
    }

    @PostMapping("/add")
    public ResponseEntity<?> getAdd(@RequestBody AttributeRequest attributeRequest) {
        Manufacturer manufacturer = new Manufacturer();
        manufacturer.setCodeManufacturer(attributeRequest.getCode().trim());
        manufacturer.setNameManufacturer(attributeRequest.getName().trim());
        manufacturer.setStatus(attributeRequest.getStatus());
        manufacturer.setCreateDate(new Date());
        manufacturer.setUpdateDate(new Date());
        this.manufacturerService.save(manufacturer);
        // Gửi thông báo tới tất cả các client về dữ liệu mới
        messagingTemplate.convertAndSend("/topic/manufacturer", manufacturer);
        messagingTemplate.convertAndSend("/topic/product", manufacturer);
        return ResponseEntity.ok(manufacturer);
    }
    @PostMapping("/update")
    public ResponseEntity<?> getUpdate(@RequestBody AttributeRequest attributeRequest) {
        Manufacturer manufacturer = manufacturerService.findById(attributeRequest.getId()).orElse(null);
        manufacturer.setCodeManufacturer(attributeRequest.getCode().trim());
        manufacturer.setNameManufacturer(attributeRequest.getName().trim());
        manufacturer.setStatus(attributeRequest.getStatus());
        manufacturer.setCreateDate(new Date());
        manufacturer.setUpdateDate(new Date());
        this.manufacturerService.save(manufacturer);
        // Gửi thông báo tới tất cả các client về dữ liệu mới
        messagingTemplate.convertAndSend("/topic/manufacturer", manufacturer);
        messagingTemplate.convertAndSend("/topic/product", manufacturer);
        return ResponseEntity.ok(manufacturer);
    }

}
