package com.fleafair.Common.Enum;

//import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.Getter;

/**
 * 商品分类枚举
 * - 固定分类适合小型项目
 * - 大型项目建议使用数据库表存储分类
 */
@Getter
public enum ItemCategory {
    ELECTRONICS(100, "电子产品", "icon-laptop"),
    CLOTHING(200, "服饰鞋包", "icon-tshirt"),
    BOOKS(300, "图书教材", "icon-book"),
    DAILY_USE(400, "生活用品", "icon-home");

//    @EnumValue
    private final int code;
    private final String name;
    private final String icon; // 前端图标

    ItemCategory(int code, String name, String icon) {
        this.code = code;
        this.name = name;
        this.icon = icon;
    }

    public static ItemCategory fromCode(int code) {
        for (ItemCategory category : values()) {
            if (category.code == code) {
                return category;
            }
        }
        throw new IllegalArgumentException("无效的分类编码: " + code);
    }
}
