package com.chatApp.websockets.repository;

import com.chatApp.websockets.entity.PrivateMessage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PrivateMessageRepository extends JpaRepository<PrivateMessage, Integer> {
    
    List<PrivateMessage> findByRoomId(int id);
}
