package com.genz.rideapp.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.genz.rideapp.model.Message;
import com.genz.rideapp.model.User;
import com.genz.rideapp.repository.UserRepository;
import com.genz.rideapp.service.MessageService;

@RestController
@RequestMapping("/api/messages")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @Autowired
    private UserRepository userRepository;

    // 1. Message Bhejo
    // URL: /api/messages/send?receiverId=2&content=Hello
    @PostMapping("/send")
    public ResponseEntity<?> sendMessage(@RequestParam Long receiverId, @RequestParam String content, Principal principal) {
        String email = principal.getName();
        User sender = userRepository.findByEmail(email).orElseThrow();
        
        Message msg = messageService.sendMessage(sender.getId(), receiverId, content);
        return ResponseEntity.ok(msg);
    }

    // 2. Chat History Nikalo
    // URL: /api/messages/history?otherUserId=2
    @GetMapping("/history")
    public List<Message> getChatHistory(@RequestParam Long otherUserId, Principal principal) {
        String email = principal.getName();
        User currentUser = userRepository.findByEmail(email).orElseThrow();
        
        return messageService.getChat(currentUser.getId(), otherUserId);
    }
}