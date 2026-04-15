package com.example.chatMulticat.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.chatMulticat.dto.request.MessageRequest;
import com.example.chatMulticat.service.MessageService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/api/messages")
@RequiredArgsConstructor
public class MessageController {

    private final MessageService messageService;

    @PostMapping
    public ResponseEntity<?> saveMessage(@RequestBody MessageRequest request) {
        return ResponseEntity.ok(messageService.saveMessage(request));
    } 
    
    @GetMapping("/{roomId}")
    public ResponseEntity<?> getMessagesOfRoom(@PathVariable Integer roomId) {
        return ResponseEntity.ok(messageService.getMessagesOfRoom(roomId));
    }
    
}
