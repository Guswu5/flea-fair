package com.fleafair.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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


    // 仅用于业务逻辑，不参与数据库映射
    private transient List<String> images;
    private String imagesJson;

    private String coverImage;
    private Integer status;
    private Integer viewCount;
    private String location;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    public String getImagesJson() {
        return imagesJson;
    }
    public void setImagesJson(String imagesJson) {
        this.imagesJson = imagesJson;
    }
    public List<String> getImages() {
        return images;
    }
    public void setImages(List<String> images) {
        this.images = images;
    }
}
