package com.example.chatMulticat.dto.request;

import lombok.Data;

@Data
public class MessageRequest {
    private Integer userId;
    private Integer roomId;
    private String content;
}
