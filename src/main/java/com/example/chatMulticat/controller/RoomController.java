package com.example.chatMulticat.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.chatMulticat.dto.request.RoomRequest;
import com.example.chatMulticat.service.RoomService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/rooms")
@RequiredArgsConstructor
public class RoomController {

    private final RoomService roomService;

    @GetMapping
    public ResponseEntity<?> getAllRoom() {
        return ResponseEntity.ok(roomService.getAllRoom());
    }

    @PostMapping
    public ResponseEntity<?> createRoom(@Valid @RequestBody RoomRequest request) {
       
        return ResponseEntity.ok(roomService.createRoom(request));
    }
}
