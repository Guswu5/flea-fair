package com.fleafair.VO;

import lombok.Data;

import java.util.List;

@Data
public class ItemDefaultVO {
    private Long id;
    private String title;
    private String price;
    private String location;

    //图片
    private String coverImage;
    private List<String> images;

}
