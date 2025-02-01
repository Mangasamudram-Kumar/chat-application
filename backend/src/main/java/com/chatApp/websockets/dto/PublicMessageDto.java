package com.chatApp.websockets.dto;

import com.chatApp.websockets.entity.User;
import lombok.Data;

@Data
public class PublicMessageDto {
    private User sender;
    private String content;
}
