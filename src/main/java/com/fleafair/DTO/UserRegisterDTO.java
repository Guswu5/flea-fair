package com.fleafair.DTO;

import lombok.Data;

/**
 * 用户注册DTO
 */
@Data
public class UserRegisterDTO {
    private String username;
    private String password;
    private String email;
    private int phone;
}
