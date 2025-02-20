package org.example.backendshop.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Controller
public class SelectionController {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    private static final String SELECTED_ROWS_KEY = "selected_rows";

    private final Map<Long, String> selectedRows = new ConcurrentHashMap<>();

    @MessageMapping("/selectRow")
    public void selectRow(Map<String, Object> payload) {
        Long rowId = Long.parseLong(payload.get("rowId").toString());
        String userId = payload.get("userId").toString();

        String existingUser = (String) redisTemplate.opsForHash().get(SELECTED_ROWS_KEY, rowId);
        if (existingUser == null || existingUser.equals(userId)) {
            redisTemplate.opsForHash().put(SELECTED_ROWS_KEY, rowId.toString(), userId);
            messagingTemplate.convertAndSend("/topic/selectedRows", selectedRows);
        }
    }

    @MessageMapping("/deselectRow")
    public void deselectRow(Map<String, Object> payload) {
        Long rowId = Long.parseLong(payload.get("rowId").toString());
        redisTemplate.opsForHash().delete(SELECTED_ROWS_KEY, rowId.toString());

        messagingTemplate.convertAndSend("/topic/selectedRows", selectedRows);
    }
}


