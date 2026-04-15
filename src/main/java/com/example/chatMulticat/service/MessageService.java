package com.example.chatMulticat.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.chatMulticat.dto.request.MessageRequest;
import com.example.chatMulticat.dto.response.MessageResponse;
import com.example.chatMulticat.model.Message;
import com.example.chatMulticat.model.Room;
import com.example.chatMulticat.model.User;
import com.example.chatMulticat.repository.MessageRepository;
import com.example.chatMulticat.repository.RoomRepository;
import com.example.chatMulticat.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MessageService {
    private final MessageRepository messageRepo;
    private final UserRepository userRepo;
    private final RoomRepository roomRepo;

    @Transactional
    public String saveMessage(MessageRequest request) {
        User user = userRepo.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy User"));
        
        Room room = roomRepo.findById(request.getRoomId())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy Room"));

        Message message = new Message();
        message.setUser(user);
        message.setRoom(room);
        message.setContent(request.getContent());

        messageRepo.save(message);
        return "Lưu tin nhắn thành công";
    }   

    public List<MessageResponse> getMessagesOfRoom(Integer roomId) {
        Room room = roomRepo.findById(roomId)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy Room"));

        List<MessageResponse> responses = new ArrayList<>();

        for (Message message : room.getMessages()) {
            MessageResponse messageResponse = new MessageResponse();
            messageResponse.setIdSender(message.getUser().getId());
            messageResponse.setSenderName(message.getUser().getUserName());
            messageResponse.setContent(message.getContent());
            messageResponse.setSentAt(message.getSentAt());

            responses.add(messageResponse);
        }

        return responses;
    }
}