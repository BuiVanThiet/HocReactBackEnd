package org.example.backendshop.restController;

import org.example.backendshop.restController.base.BaseRestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:3000/")  // URL của React app
@RequestMapping("/user-manage")
public class UserRestController extends BaseRestController {
    private final SimpMessagingTemplate messagingTemplate;
    @Autowired
    public UserRestController(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

}
