package com.fleafair.DTO;

import lombok.Data;

@Data
public class LoginDTO {
    private Long userId;
    private String phone;
    private String password;
}
