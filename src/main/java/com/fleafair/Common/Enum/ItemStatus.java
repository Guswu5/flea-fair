package com.fleafair.Common.Enum;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * 商品状态枚举（纯MyBatis方案）
 * - 数据库存储code值（int）
 * - 支持前后端交互自动转换
 */
public enum ItemStatus {
    ON_SALE(1, "在售"),
    SOLD(0, "已售出"),
    OFF_SHELF(2, "已下架"),
    UNDER_REVIEW(3, "审核中");

    private final int code;
    private final String desc;

    ItemStatus(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    // 用于MyBatis从数据库读取时转换
    public static ItemStatus fromCode(int code) {
        for (ItemStatus status : values()) {
            if (status.code == code) {
                return status;
            }
        }
        throw new IllegalArgumentException("无效状态码: " + code);
    }

    // 存到数据库的值
    public int getCode() {
        return code;
    }

    // 返回给前端的描述
    @JsonValue
    public String getDesc() {
        return desc;
    }

    // 前端传参时反序列化
    @JsonCreator
    public static ItemStatus fromRequest(String name) {
        return valueOf(name.toUpperCase());
    }
}
