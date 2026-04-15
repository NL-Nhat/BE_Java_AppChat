package com.example.chatMulticat.dto.response;

import lombok.Data;

@Data
public class RoomOfMemberResponse {

    private Integer idRoom;
    private String roomName;
    private String multicastIp;
}