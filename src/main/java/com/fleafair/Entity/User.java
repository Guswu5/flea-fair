package com.fleafair.Entity;

import lombok.Data;

@Data
public class User {
    private Long id;
    private Long userId;// ==  id +10000
    private String username; // 用户名
    private String password;
    private String email;
    private String phone;
    private String avatar;// 头像
    private int status;// 0: 未激活 1: 已激活
}