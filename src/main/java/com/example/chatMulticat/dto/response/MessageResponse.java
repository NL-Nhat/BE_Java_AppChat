package com.example.chatMulticat.dto.response;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageResponse {

    private Integer idSender;
    private String senderName;
    private String content;
    private LocalDateTime sentAt;
}
