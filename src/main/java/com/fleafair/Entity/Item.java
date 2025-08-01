package com.fleafair.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fleafair.Common.Enum.ItemStatus;
import lombok.Data;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;
import org.apache.ibatis.type.EnumOrdinalTypeHandler;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Data
public class Item {
    private Long id;
    private Long userId;
    private Integer categoryId;
    private String title;
    private String details;  // 改名为details
    private BigDecimal price;    // 金额建议使用BigDecimal
    private List<String> images = new ArrayList<>();  // 初始化

    // 封面图可以从images列表获取，可能不需要单独存储
    // private String coverImage;

    private ItemStatus status;   // 改为枚举类型
    private Integer viewCount = 0; // 改为普通int类型
    private String location;

    private LocalDateTime createTime;
    private LocalDateTime updateTime;

    // 获取封面图的便捷方法
    public String getCoverImage() {
        return !images.isEmpty() ? images.get(0) : null;
    }
}


