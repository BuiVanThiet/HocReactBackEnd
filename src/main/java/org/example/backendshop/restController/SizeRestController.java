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
@RequestMapping("/size-manage")
public class SizeRestController extends BaseRestController {
    private final SimpMessagingTemplate messagingTemplate;
    @Autowired
    public SizeRestController(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }
    @GetMapping("/all-object")
    public List<Object[]> getAllColorObject() {
        return sizeService.getAllSize();
    }

    @PostMapping("/add")
    public ResponseEntity<?> getAdd(@RequestBody AttributeRequest attributeRequest) {
        Size size = new Size();
        size.setCodeSize(attributeRequest.getCode().trim());
        size.setNameSize(attributeRequest.getName().trim());
        size.setStatus(attributeRequest.getStatus());
        size.setCreateDate(new Date());
        size.setUpdateDate(new Date());
        this.sizeService.save(size);
        // Gửi thông báo tới tất cả các client về dữ liệu mới
        messagingTemplate.convertAndSend("/topic/size", size);
        messagingTemplate.convertAndSend("/topic/product", size);
        return ResponseEntity.ok(size);
    }
    @PostMapping("/update")
    public ResponseEntity<?> getUpdate(@RequestBody AttributeRequest attributeRequest) {
        Size size = this.sizeService.findById(attributeRequest.getId()).orElse(null);
        size.setCodeSize(attributeRequest.getCode().trim());
        size.setNameSize(attributeRequest.getName().trim());
        size.setStatus(attributeRequest.getStatus());
        size.setCreateDate(new Date());
        size.setUpdateDate(new Date());
        this.sizeService.save(size);
        // Gửi thông báo tới tất cả các client về dữ liệu mới
        messagingTemplate.convertAndSend("/topic/size", size);
        messagingTemplate.convertAndSend("/topic/product", size);
        return ResponseEntity.ok(size);
    }

    @GetMapping("/size-by-product/{idPR}")
    public List<Object[]> getSizeByIdPR(@PathVariable("idPR") String idPR) {
        return sizeService.getSizeByProduct(Integer.parseInt(idPR));
    }
}
