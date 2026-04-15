package com.example.chatMulticat.dto.request;

import lombok.Data;

@Data
public class JoinRoomRequest {
    private Integer userId;
    private String multicastIp;
    private String pass;
}
