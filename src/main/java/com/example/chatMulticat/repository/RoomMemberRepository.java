package com.example.chatMulticat.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.chatMulticat.model.Room;
import com.example.chatMulticat.model.RoomMember;
import com.example.chatMulticat.model.User;

public interface RoomMemberRepository extends JpaRepository<RoomMember, Integer>{

    List<RoomMember> findByUser(User user);

    boolean existsByUserAndRoom(User user, Room room);

    public RoomMember findByUserAndRoom(User user, Room room);
}
