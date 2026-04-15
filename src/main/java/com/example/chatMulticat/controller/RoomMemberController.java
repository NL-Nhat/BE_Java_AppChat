package com.example.chatMulticat.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.chatMulticat.dto.request.JoinRoomRequest;
import com.example.chatMulticat.service.RoomMemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/room-member")
public class RoomMemberController {

    private final RoomMemberService roomMemberService;

    @GetMapping("/rooms-of-user/{userId}")
    public ResponseEntity<?> getAllRoomByUser(@PathVariable Integer userId) {

        return ResponseEntity.ok(roomMemberService.getAllRoomOfUser(userId));
    }

    @GetMapping("/users-of-room/{roomId}")
    public ResponseEntity<?> getAllUserOfRoom(@PathVariable Integer roomId) {

        return ResponseEntity.ok(roomMemberService.getAllUserOfRoom(roomId));
    }

    @PostMapping
    public ResponseEntity<?> joinRoom(@RequestBody JoinRoomRequest request) {
        
        return ResponseEntity.ok(roomMemberService.joinRoom(request));
    }

    @DeleteMapping("/{roomId}/{userId}")
    public ResponseEntity<?> quitRoom(
            @PathVariable Integer roomId,
            @PathVariable Integer userId) {

        roomMemberService.quitRoom(userId, roomId);
        
        return ResponseEntity.ok("Rời nhóm thành công");
    }
}
