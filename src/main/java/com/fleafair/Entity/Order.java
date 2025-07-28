package com.fleafair.Entity;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
//@Builder
public class Order {
    private Long id;              // 订单ID，自增主键
    private Long itemId;          // 商品ID

    //TODO: 订单表添加用户地址字段
    private Long addressId;       // 用户地址
    private Integer status;   // 订单状态，默认0（待支付）
    private LocalDateTime createTime;      // 订单创建时间
    private LocalDateTime updateTime;      // 订单更新时间

    // 状态常量定义
    public static final int PENDING_PAYMENT = 0;  // 待支付
    public static final int PAID = 1;             // 已支付
    public static final int SHIPPED = 2;          // 已发货
    public static final int COMPLETED = 3;        // 已完成
    public static final int CANCELLED = 4;        // 已取消
}
