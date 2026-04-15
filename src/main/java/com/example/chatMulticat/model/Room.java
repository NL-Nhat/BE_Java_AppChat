package com.example.chatMulticat.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity 
@Table(name = "Rooms")
@Data
public class Room {

     @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_id")
    private Integer id;
    
    @Column(name = "roomName", nullable = false)
    private String roomName;

    @Column(name = "pass", nullable = false)
    private String pass;
    
    @Column(name = "multicastIp", nullable = false, unique = true)
    private String multicastIp;
    
    @Column(name = "createdAt", nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @OneToMany(mappedBy = "room")
    private List<RoomMember> roomMembers = new ArrayList<RoomMember>();

    @OneToMany(mappedBy = "room")
    private List<Message> messages = new ArrayList<Message>();
}