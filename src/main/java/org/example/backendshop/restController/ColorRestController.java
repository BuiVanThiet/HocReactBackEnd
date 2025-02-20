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
@RequestMapping("/color-manage")
public class ColorRestController extends BaseRestController {
    private final SimpMessagingTemplate messagingTemplate;
    @Autowired
    public ColorRestController(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @GetMapping("/all-object")
    public List<Object[]> getAllColorObject() {
        return colorService.getAllColor();
    }
    @PostMapping("/add")
    public ResponseEntity<?> getAdd(@RequestBody AttributeRequest attributeRequest) {
        Color color = new Color();
        color.setCodeColor(attributeRequest.getCode().trim());
        color.setNameColor(attributeRequest.getName().trim());
        color.setStatus(attributeRequest.getStatus());
        color.setCreateDate(new Date());
        color.setUpdateDate(new Date());
        this.colorService.save(color);
        // Gửi thông báo tới tất cả các client về dữ liệu mới
        messagingTemplate.convertAndSend("/topic/color", color);
        messagingTemplate.convertAndSend("/topic/product", color);
        return ResponseEntity.ok(color);
    }

    @PostMapping("/update")
    public ResponseEntity<?> getUpdate(@RequestBody AttributeRequest attributeRequest) {
        Color color = this.colorService.findById(attributeRequest.getId()).orElse(null);
        color.setCodeColor(attributeRequest.getCode().trim());
        color.setNameColor(attributeRequest.getName().trim());
        color.setStatus(attributeRequest.getStatus());
        color.setUpdateDate(new Date());
        this.colorService.save(color);
        // Gửi thông báo tới tất cả các client về dữ liệu mới
        messagingTemplate.convertAndSend("/topic/color", color);
        messagingTemplate.convertAndSend("/topic/product", color);
        return ResponseEntity.ok(color);
    }

    @GetMapping("/color-by-product/{idPR}")
    public List<Object[]> getColorByIdPR(@PathVariable("idPR") String idPR) {
        return colorService.getColorByProduct(Integer.parseInt(idPR));
    }

}
