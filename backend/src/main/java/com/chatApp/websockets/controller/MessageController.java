package com.chatApp.websockets.controller;

import com.chatApp.websockets.dto.PrivateMessageDto;
import com.chatApp.websockets.dto.PublicMessageDto;
import com.chatApp.websockets.entity.PrivateMessage;
import com.chatApp.websockets.entity.PublicMessage;
import com.chatApp.websockets.entity.User;
import com.chatApp.websockets.service.MessageService;
import com.chatApp.websockets.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//@Controller
@RestController
@CrossOrigin
public class MessageController {
    private MessageService messageService;
    private UserService userService;
    private SimpMessagingTemplate simpMessagingTemplate;
    
    @Autowired
    public MessageController(MessageService messageService, UserService userService, SimpMessagingTemplate simpMessagingTemplate) {
        this.messageService = messageService;
        this.userService = userService;
        this.simpMessagingTemplate = simpMessagingTemplate;
    }
    
    
    @MessageMapping("/chat/private")
    public void sendMessage(@Payload PrivateMessageDto message) {
        PrivateMessage savedMessage = messageService.save(message);
        User receiver = userService.find(message.getReceiver().getId());
        simpMessagingTemplate.convertAndSendToUser(
            String.valueOf(receiver.getId()),
            "/queue/messages",
            savedMessage
        );
    }
    
    @MessageMapping("/chat/public")
    @SendTo("/public")
    public PublicMessage sendMessage(@Payload PublicMessageDto message) {
        return messageService.save(message);
    }
    
    @GetMapping("/messages/{senderId}/{receiverId}")
    public List<PrivateMessage> getMessages(@PathVariable int senderId, @PathVariable int receiverId) {
        return messageService.getMessages(senderId, receiverId);
    }
}
