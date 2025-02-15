package com.chatApp.websockets.controller;


import com.chatApp.websockets.dto.SignInDto;
import com.chatApp.websockets.entity.User;
import com.chatApp.websockets.service.UserService;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@Controller
@RestController
@CrossOrigin
public class UserController {
    private UserService userService;
    
    public UserController(UserService userService) {
        this.userService = userService;
    }
    
    @MessageMapping("/user.addUser")
    @SendTo("/online")
    public User addUser(@Payload  User user) {
        return userService.connect(user);
    }
    
    @MessageMapping("/user.disconnectUser")
    @SendTo("/online")
    public User disconnect(@Payload  User user) {
        return userService.disconnect(user);
    }
    
    @GetMapping("/users")
    public List<User> getConnectedUsers() {
        return userService.getConnectedUsers();
    }
    
    @PostMapping("/signin")
    public User signIn (@RequestBody SignInDto signInInfo ) {
        return userService.loadUser(signInInfo);
    }
    
}
