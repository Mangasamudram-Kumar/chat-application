package com.chatApp.websockets.repository;

import com.chatApp.websockets.config.Status;
import com.chatApp.websockets.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer>{
    List<User> findByStatus(Status status);
    
    Optional<User> findByUsername(String username);
}
