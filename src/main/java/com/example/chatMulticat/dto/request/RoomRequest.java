package com.example.chatMulticat.dto.request;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class RoomRequest {

    @NotNull(message = "UserId không được null")
    @Min(value = 1, message = "id phải >= 1")
    private Integer userId;

    @NotBlank(message = "Tên phòng không được để trống")
    @Size(min = 3, max = 50, message = "Tên phòng phải từ 3 đến 50 ký tự")
    private String roomName;

    @NotBlank(message = "Mật khẩu không được để trống")
    @Size(min = 4, max = 20, message = "Mật khẩu phải từ 4 đến 20 ký tự")
    private String pass;

    @NotBlank(message = "IP không được để trống")
    @Pattern(
        regexp = "^(22[4-9]|23[0-9])\\.(25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)$",
        message = "IP multicast phải nằm trong dải 224.0.0.0 - 239.255.255.255"
    )
    private String multicastIp;
}