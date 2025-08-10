package com.fleafair.Entity;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class ChatSession {
    private Long id;
    private Long user1Id;     // 用户1的ID
    private Long user2Id;     // 用户2的ID
    private Long itemId;      // 关联的商品ID（可选）
    private String lastMessage; // 最后一条消息内容
    private LocalDateTime lastMessageTime; // 最后消息时间
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    
    // 关联查询字段
    private User user1;
    private User user2;
    private Item item;
    private Integer unreadCount; // 未读消息数量
} 