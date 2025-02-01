package com.chatApp.websockets.repository;

import com.chatApp.websockets.entity.PublicMessage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PublicMessageRepository extends JpaRepository<PublicMessage, Integer> {
}
