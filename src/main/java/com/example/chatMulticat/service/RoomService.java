package com.example.chatMulticat.service;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.chatMulticat.dto.request.RoomRequest;
import com.example.chatMulticat.dto.response.RoomResponse;
import com.example.chatMulticat.model.Room;
import com.example.chatMulticat.model.RoomMember;
import com.example.chatMulticat.model.User;
import com.example.chatMulticat.repository.RoomMemberRepository;
import com.example.chatMulticat.repository.RoomRepository;
import com.example.chatMulticat.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RoomService {

    private final RoomRepository roomRepository;
    private final UserRepository userRepository;
    private final RoomMemberRepository roomMemberRepository;

    @Transactional
    public List<RoomResponse> getAllRoom() {
        List<Room> rooms = roomRepository.findAll();

        List<RoomResponse> roomResponses = new ArrayList<>();

        for (Room room : rooms) {
            RoomResponse response = new RoomResponse();
            response.setId(room.getId());
            response.setMulticastIp(room.getMulticastIp());
            response.setRoomName(room.getRoomName());

            List<Integer> listIdUser = new ArrayList<>();

            for (RoomMember roomMember : room.getRoomMembers()) {
                listIdUser.add(roomMember.getUser().getId());
            }

            response.setIdUser(listIdUser);

            roomResponses.add(response);
        }

        return roomResponses;
    }

    @Transactional
    public Map<String, Object> createRoom(RoomRequest request) {

        User user = userRepository.findById(request.getUserId())
            .orElseThrow(() -> new RuntimeException("Ko tìm thấy user với id này"));
        
        if(roomRepository.existsByMulticastIp(request.getMulticastIp())) {
            throw new RuntimeException("MulticastIp đã được sử dụng");
        }

        try {
            InetAddress addr = InetAddress.getByName(request.getMulticastIp());
            if (!addr.isMulticastAddress()) {
                throw new RuntimeException("IP không phải multicast");
            }
        } catch (Exception e) {
            throw new RuntimeException("IP không hợp lệ");
        }

        Room room = new Room();
        room.setMulticastIp(request.getMulticastIp());
        room.setPass(request.getPass());
        room.setRoomName(request.getRoomName());

        Room saveRoom = roomRepository.save(room);

        RoomMember roomMember = new RoomMember();
        roomMember.setUser(user);
        roomMember.setRoom(saveRoom);

        roomMemberRepository.save(roomMember);

        Map<String, Object> response = new HashMap<>();
        response.put("roomId", saveRoom.getId());
        response.put("message", "Tạo nhóm thành công");

        return response;
    }
}
