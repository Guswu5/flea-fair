package com.fleafair.DTO;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class ItemReleaseDTO {
    private String title;
    private String details;  // 原为description
    private BigDecimal price;
    private Integer categoryId;
    private List<String> images;
    private String location;
}
