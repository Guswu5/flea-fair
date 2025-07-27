package com.fleafair.VO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ItemVO {
    private Long id;
    private String title;
    private String desc;
    private BigDecimal price;
    private transient List<String> images;

    //封装用户和用户名
    //"seller": { "userId": 1, "nickname": "小明" }
    private UserVO seller;

}
