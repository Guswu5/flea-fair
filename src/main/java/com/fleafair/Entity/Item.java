package com.fleafair.Entity;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;


@Data
public class Item {
    private Long id;
    private Long userId;
    private Integer categoryId;
    private String title;
    private String desc;//描述
    private BigDecimal price;


    private List<String> images;

    private String coverImage;
    private Integer status;
    private Integer viewCount;
    private String location;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;
}
