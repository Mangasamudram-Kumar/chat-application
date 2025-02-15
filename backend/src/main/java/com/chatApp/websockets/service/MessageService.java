package com.chatApp.websockets.service;

import com.chatApp.websockets.dto.PrivateMessageDto;
import com.chatApp.websockets.dto.PublicMessageDto;
import com.chatApp.websockets.entity.PrivateMessage;
import com.chatApp.websockets.entity.PublicMessage;
import com.chatApp.websockets.entity.Room;
import com.chatApp.websockets.entity.User;
import com.chatApp.websockets.repository.PrivateMessageRepository;
import com.chatApp.websockets.repository.PublicMessageRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {
    private PrivateMessageRepository privateMessageRepository;
    private PublicMessageRepository publicMessageRepository;
    private RoomService roomService;
    private UserService userService;
    
    public MessageService(
        PrivateMessageRepository privateMessageRepository,
        PublicMessageRepository publicMessageRepository,
        RoomService roomService,
        UserService userService
    ) {
        this.privateMessageRepository = privateMessageRepository;
        this.publicMessageRepository = publicMessageRepository;
        this.roomService = roomService;
        this.userService = userService;
    }
    
    public PrivateMessage save(PrivateMessageDto message) {
        Room room = roomService.getRoom(
                message.getSender().getId(),
                message.getReceiver().getId(),
                true
        );
        if (room == null) return null;
        User sender = userService.find(message.getSender().getId());
        User receiver = userService.find(message.getReceiver().getId());
        if (sender == null || receiver == null ) return null;
        PrivateMessage newMessage = PrivateMessage.builder()
            .sender(sender)
            .receiver(receiver)
            .content(message.getContent())
            .roomId(room.getId())
            .build();
        return privateMessageRepository.save(newMessage);
    }
    
    public PublicMessage save(PublicMessageDto message) {
        User sender = userService.find(message.getSender().getId());
        if (sender == null) return null;
        PublicMessage newMessage = PublicMessage.builder()
            .sender(sender)
            .content(message.getContent())
            .build();
        return publicMessageRepository.save(newMessage);
    }
    
    public List<PrivateMessage> getMessages(int senderId, int receiverId) {
        Room room = roomService.getRoom(senderId, receiverId, false);
        if (room != null) {
            return privateMessageRepository.findByRoomId(room.getId());
        }
        return null;
    }
}
