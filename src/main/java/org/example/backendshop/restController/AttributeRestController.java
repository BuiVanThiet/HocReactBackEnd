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
@RequestMapping("/attribute-manage")
@CrossOrigin(origins = "http://localhost:3000/")  // URL của React app
public class AttributeRestController extends BaseRestController {
    private final SimpMessagingTemplate messagingTemplate;
    @Autowired
    public AttributeRestController(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @GetMapping("/all-object/{typeAttribute}")
    public List<Object[]> getAllColorObject(@PathVariable(name = "typeAttribute") String typeAttribute) {
        System.out.println("da chay vo day");
        System.out.println(typeAttribute);
        List<Object[]> list = new ArrayList<>();
        if(Integer.parseInt(typeAttribute) == 1) {
            list = colorService.getAllColor();
        }else if (Integer.parseInt(typeAttribute) == 2) {
            list = categoryService.getAllCategory();
        }else if (Integer.parseInt(typeAttribute) == 3) {
            list = manufacturerService.getAllManufacturer();
        }else if (Integer.parseInt(typeAttribute) == 4) {
            list = originService.getAllOrigin();
        }else if (Integer.parseInt(typeAttribute) == 5) {
            list = sizeService.getAllSize();
        }else if (Integer.parseInt(typeAttribute) == 6) {
            list = weightTypeService.getAllWeightType();
        }
        return list;
    }

    @PostMapping("/add/{typeAttribute}")
    public ResponseEntity<?> getAdd(@RequestBody AttributeRequest attributeRequest,@PathVariable(name = "typeAttribute") String typeAttribute) {
        System.out.println(attributeRequest.toString());
        if(Integer.parseInt(typeAttribute) == 1) {
            Color color = new Color();
            color.setCodeColor(attributeRequest.getCode().trim());
            color.setNameColor(attributeRequest.getName().trim());
            color.setStatus(attributeRequest.getStatus());
            color.setCreateDate(new Date());
            color.setUpdateDate(new Date());
            System.out.println("them "+typeAttribute);
            this.colorService.save(color);
            // Gửi thông báo tới tất cả các client về dữ liệu mới
            messagingTemplate.convertAndSend("/topic/attribute", color);
            return ResponseEntity.ok(color);
        }else if (Integer.parseInt(typeAttribute) == 2) {
            Category category = new Category();
            category.setCodeCategory(attributeRequest.getCode().trim());
            category.setNameCategory(attributeRequest.getName().trim());
            category.setStatus(attributeRequest.getStatus());
            category.setCreateDate(new Date());
            category.setUpdateDate(new Date());
            System.out.println("them "+typeAttribute);
            this.categoryService.save(category);
            // Gửi thông báo tới tất cả các client về dữ liệu mới
            messagingTemplate.convertAndSend("/topic/attribute", category);
            return ResponseEntity.ok(category);
        }else if (Integer.parseInt(typeAttribute) == 3) {
            Manufacturer manufacturer = new Manufacturer();
            manufacturer.setCodeManufacturer(attributeRequest.getCode().trim());
            manufacturer.setNameManufacturer(attributeRequest.getName().trim());
            manufacturer.setStatus(attributeRequest.getStatus());
            manufacturer.setCreateDate(new Date());
            manufacturer.setUpdateDate(new Date());
            System.out.println("them "+typeAttribute);
            this.manufacturerService.save(manufacturer);
            // Gửi thông báo tới tất cả các client về dữ liệu mới
            messagingTemplate.convertAndSend("/topic/attribute", manufacturer);
            return ResponseEntity.ok(manufacturer);
        }else if (Integer.parseInt(typeAttribute) == 4) {
            Origin origin = new Origin();
            origin.setCodeOrigin(attributeRequest.getCode().trim());
            origin.setNameOrigin(attributeRequest.getName().trim());
            origin.setStatus(attributeRequest.getStatus());
            origin.setCreateDate(new Date());
            origin.setUpdateDate(new Date());
            System.out.println("them "+typeAttribute);
            this.originService.save(origin);
            // Gửi thông báo tới tất cả các client về dữ liệu mới
            messagingTemplate.convertAndSend("/topic/attribute", origin);
            return ResponseEntity.ok(origin);
        }else if (Integer.parseInt(typeAttribute) == 5) {
            Size size = new Size();
            size.setCodeSize(attributeRequest.getCode().trim());
            size.setNameSize(attributeRequest.getName().trim());
            size.setStatus(attributeRequest.getStatus());
            size.setCreateDate(new Date());
            size.setUpdateDate(new Date());
            System.out.println("them "+typeAttribute);
            this.sizeService.save(size);
            // Gửi thông báo tới tất cả các client về dữ liệu mới
            messagingTemplate.convertAndSend("/topic/attribute", size);
            return ResponseEntity.ok(size);
        }else if (Integer.parseInt(typeAttribute) == 6) {
            WeightType weightType = new WeightType();
            weightType.setCodeWeightType(attributeRequest.getCode().trim());
            weightType.setNameWeightType(attributeRequest.getName().trim());
            weightType.setStatus(attributeRequest.getStatus());
            weightType.setCreateDate(new Date());
            weightType.setUpdateDate(new Date());
            System.out.println("them "+typeAttribute);
            this.weightTypeService.save(weightType);
            // Gửi thông báo tới tất cả các client về dữ liệu mới
            messagingTemplate.convertAndSend("/topic/attribute", weightType);
            return ResponseEntity.ok(weightType);
        }
        return ResponseEntity.ok(null);
    }
    @PostMapping("/update/{typeAttribute}")
    public ResponseEntity<?> getUpdate(@RequestBody AttributeRequest attributeRequest,@PathVariable(name = "typeAttribute") String typeAttribute) {
        System.out.println(attributeRequest.toString());

        if(Integer.parseInt(typeAttribute) == 1) {
            Color color = this.colorService.findById(attributeRequest.getId()).orElse(null);
            color.setCodeColor(attributeRequest.getCode().trim());
            color.setNameColor(attributeRequest.getName().trim());
            color.setStatus(attributeRequest.getStatus());
            color.setUpdateDate(new Date());
            this.colorService.save(color);
            // Gửi thông báo tới tất cả các client về dữ liệu mới
            messagingTemplate.convertAndSend("/topic/attribute", color);
            return ResponseEntity.ok(color);
        }else if (Integer.parseInt(typeAttribute) == 2) {
            Category category = categoryService.findById(attributeRequest.getId()).orElse(null);
            category.setCodeCategory(attributeRequest.getCode().trim());
            category.setNameCategory(attributeRequest.getName().trim());
            category.setStatus(attributeRequest.getStatus());
            category.setUpdateDate(new Date());
            System.out.println("them "+typeAttribute);
            this.categoryService.save(category);
            // Gửi thông báo tới tất cả các client về dữ liệu mới
            messagingTemplate.convertAndSend("/topic/attribute", category);
            return ResponseEntity.ok(category);
        }else if (Integer.parseInt(typeAttribute) == 3) {
            Manufacturer manufacturer = manufacturerService.findById(attributeRequest.getId()).orElse(null);
            manufacturer.setCodeManufacturer(attributeRequest.getCode().trim());
            manufacturer.setNameManufacturer(attributeRequest.getName().trim());
            manufacturer.setStatus(attributeRequest.getStatus());
            manufacturer.setCreateDate(new Date());
            manufacturer.setUpdateDate(new Date());
            System.out.println("them "+typeAttribute);
            this.manufacturerService.save(manufacturer);
            // Gửi thông báo tới tất cả các client về dữ liệu mới
            messagingTemplate.convertAndSend("/topic/attribute", manufacturer);
            return ResponseEntity.ok(manufacturer);
        }else if (Integer.parseInt(typeAttribute) == 4) {
            Origin origin = this.originService.findById(attributeRequest.getId()).orElse(null);
            origin.setCodeOrigin(attributeRequest.getCode().trim());
            origin.setNameOrigin(attributeRequest.getName().trim());
            origin.setStatus(attributeRequest.getStatus());
            origin.setCreateDate(new Date());
            origin.setUpdateDate(new Date());
            System.out.println("them "+typeAttribute);
            this.originService.save(origin);
            // Gửi thông báo tới tất cả các client về dữ liệu mới
            messagingTemplate.convertAndSend("/topic/attribute", origin);
            return ResponseEntity.ok(origin);
        }else if (Integer.parseInt(typeAttribute) == 5) {
            Size size = this.sizeService.findById(attributeRequest.getId()).orElse(null);
            size.setCodeSize(attributeRequest.getCode().trim());
            size.setNameSize(attributeRequest.getName().trim());
            size.setStatus(attributeRequest.getStatus());
            size.setCreateDate(new Date());
            size.setUpdateDate(new Date());
            System.out.println("them "+typeAttribute);
            this.sizeService.save(size);
            // Gửi thông báo tới tất cả các client về dữ liệu mới
            messagingTemplate.convertAndSend("/topic/attribute", size);
            return ResponseEntity.ok(size);
        }else if (Integer.parseInt(typeAttribute) == 6) {
            WeightType weightType = this.weightTypeService.findById(attributeRequest.getId()).orElse(null);
            weightType.setCodeWeightType(attributeRequest.getCode().trim());
            weightType.setNameWeightType(attributeRequest.getName().trim());
            weightType.setStatus(attributeRequest.getStatus());
            weightType.setCreateDate(new Date());
            weightType.setUpdateDate(new Date());
            System.out.println("them "+typeAttribute);
            this.weightTypeService.save(weightType);
            // Gửi thông báo tới tất cả các client về dữ liệu mới
            messagingTemplate.convertAndSend("/topic/attribute", weightType);
            return ResponseEntity.ok(weightType);
        }
        return ResponseEntity.ok(null);
    }

}
