package com.example.chatMulticat.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.chatMulticat.model.Room;


public interface RoomRepository extends JpaRepository<Room, Integer>{

    public Room findByMulticastIp(String multicastIp);

    public boolean existsByMulticastIp(String multicastIp);
}
