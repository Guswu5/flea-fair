package com.fleafair.DTO;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class MessageDTO {
    private String type; // text, image, order_request, system
    private Long fromUserId;
    private Long toUserId;
    private String content;
    private String imageUrl;
    private Long itemId;
    private String orderInfo;
    private LocalDateTime timestamp;
    private Long messageId;
    
    // 用于订单请求的额外信息
    private String itemTitle;
    private String itemImage;
    private Double itemPrice;
} 