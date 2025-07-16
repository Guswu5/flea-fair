package com.fleafair.Entity;

import lombok.Data;

@Data
public class User {
    private Long id;
    private String username; // 用户名
    private String password;
    private String email;
    private String phone;
    private String role;
}