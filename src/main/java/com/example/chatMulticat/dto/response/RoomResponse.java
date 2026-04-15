package com.example.chatMulticat.dto.response;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class RoomResponse {

    private Integer id;
    private String roomName;
    private String multicastIp;

    List<Integer> idUser = new ArrayList<>();
}
