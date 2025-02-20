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
@RequestMapping("/category-manage")
public class CategoryRestController extends BaseRestController {
    private final SimpMessagingTemplate messagingTemplate;
    @Autowired
    public CategoryRestController(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @GetMapping("/all-object")
    public List<Object[]> getAllColorObject() {
        return categoryService.getAllCategory();
    }

    @PostMapping("/add")
    public ResponseEntity<?> getAdd(@RequestBody AttributeRequest attributeRequest, @PathVariable(name = "typeAttribute") String typeAttribute) {
        Category category = new Category();
        category.setCodeCategory(attributeRequest.getCode().trim());
        category.setNameCategory(attributeRequest.getName().trim());
        category.setStatus(attributeRequest.getStatus());
        category.setCreateDate(new Date());
        category.setUpdateDate(new Date());
        System.out.println("them "+typeAttribute);
        this.categoryService.save(category);
        // Gửi thông báo tới tất cả các client về dữ liệu mới
        messagingTemplate.convertAndSend("/topic/category", category);
        messagingTemplate.convertAndSend("/topic/product", category);
        return ResponseEntity.ok(category);
    }
    @PostMapping("/update")
    public ResponseEntity<?> getUpdate(@RequestBody AttributeRequest attributeRequest) {
        Category category = categoryService.findById(attributeRequest.getId()).orElse(null);
        category.setCodeCategory(attributeRequest.getCode().trim());
        category.setNameCategory(attributeRequest.getName().trim());
        category.setStatus(attributeRequest.getStatus());
        category.setUpdateDate(new Date());
        this.categoryService.save(category);
        // Gửi thông báo tới tất cả các client về dữ liệu mới
        messagingTemplate.convertAndSend("/topic/category", category);
        messagingTemplate.convertAndSend("/topic/product", category);
        return ResponseEntity.ok(category);
    }
}
