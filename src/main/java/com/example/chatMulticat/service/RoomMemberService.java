package com.example.chatMulticat.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.chatMulticat.dto.request.JoinRoomRequest;
import com.example.chatMulticat.dto.response.RoomOfMemberResponse;
import com.example.chatMulticat.model.Room;
import com.example.chatMulticat.model.RoomMember;
import com.example.chatMulticat.model.User;
import com.example.chatMulticat.repository.RoomMemberRepository;
import com.example.chatMulticat.repository.RoomRepository;
import com.example.chatMulticat.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RoomMemberService {

    private final UserRepository userRepository;
    private final RoomRepository roomRepository;
    private final RoomMemberRepository roomMemberRepository;

    public List<RoomOfMemberResponse> getAllRoomOfUser(Integer userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("Ko tim thay user voi id nay"));

        List<RoomOfMemberResponse> rMemberResponses = new ArrayList<>();

        for (RoomMember rMember : user.getRoomMembers()) {
            RoomOfMemberResponse response = new RoomOfMemberResponse();
            response.setIdRoom(rMember.getRoom().getId());
            response.setMulticastIp(rMember.getRoom().getMulticastIp());
            response.setRoomName(rMember.getRoom().getRoomName());

            rMemberResponses.add(response);
        }

        return rMemberResponses;
    }

    public List<String> getAllUserOfRoom(Integer roomId) {
        Room room = roomRepository.findById(roomId).orElseThrow(() -> new RuntimeException("Ko tim thay room voi id nay"));

        List<String> result = new ArrayList<>();

        for (RoomMember rMember : room.getRoomMembers()) {
            
            String userName = rMember.getUser().getUserName();

            result.add(userName);
        }

        return result;
    }

    @Transactional
    public Map<String, Object> joinRoom(JoinRoomRequest request) {
        Room room = roomRepository.findByMulticastIp(request.getMulticastIp());

        if(room == null) {
            throw new RuntimeException("Ko tim thay room voi multicastIp nay");
        }

        if (request.getPass() == null || !request.getPass().equals(room.getPass())) {
            throw new RuntimeException("Sai mật khẩu phòng");
        }
        
        User user = userRepository.findById(request.getUserId()).orElseThrow(() -> new RuntimeException("Ko tim thay user voi id nay"));

        if (roomMemberRepository.existsByUserAndRoom(user, room)) {
            throw new RuntimeException("User đã tham gia room rồi");
        }

        RoomMember roomMember = new RoomMember();
        roomMember.setRoom(room);
        roomMember.setUser(user);

        roomMemberRepository.save(roomMember);

        Map<String, Object> result = new HashMap<>();
        result.put("idRoom", room.getId());
        result.put("message", "Tham gia phòng thành công");

        return result;
    }

    @Transactional
    public void quitRoom(Integer userId, Integer roomId) {

        if (userId == null || roomId == null) {
            throw new RuntimeException("Thiếu dữ liệu đầu vào");
        }

        User user = userRepository.findById(userId)
            .orElseThrow(() -> new RuntimeException("Ko tìm thấy user vói id này"));

        Room room = roomRepository.findById(roomId)
            .orElseThrow(() -> new RuntimeException("Ko tìm thấy room vói id này"));

        RoomMember roomMember = roomMemberRepository.findByUserAndRoom(user, room);

        if (roomMember == null) {
            throw new RuntimeException("User chưa tham gia phòng này");
        }

        roomMemberRepository.delete(roomMember);
    }
}
