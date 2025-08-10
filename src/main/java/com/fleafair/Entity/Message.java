package com.fleafair.Entity;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class Message {
    private Long id;
    private Long fromUserId;  // 发送者用户ID
    private Long toUserId;    // 接收者用户ID
    private String content;   // 消息内容
    private String messageType; // 消息类型：text, image, order_request
    private String imageUrl;  // 如果是图片消息，存储图片URL
    private Long itemId;      // 如果是订单请求，存储商品ID
    private String orderInfo; // 订单相关信息（JSON格式）
    private Boolean isRead;   // 是否已读
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    
    // 关联查询字段
    private User fromUser;
    private User toUser;
    private Item item;
} 