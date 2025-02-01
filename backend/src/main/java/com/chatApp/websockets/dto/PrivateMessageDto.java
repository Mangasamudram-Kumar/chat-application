package com.chatApp.websockets.dto;

import com.chatApp.websockets.entity.User;
import jakarta.persistence.Column;
import lombok.Data;

@Data
public class PrivateMessageDto {
    private User sender;
    private User receiver;
    private String content;
}
