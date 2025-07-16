package com.fleafair.DTO;

import lombok.Data;

@Data
public class UserUpdateDTO {
    private Long id;
    private String phone;
    private String email;
    private String newPassword;
    private String currentPassword; // 必填，用于验证
}
