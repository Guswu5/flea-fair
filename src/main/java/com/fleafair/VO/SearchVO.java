package com.fleafair.VO;

import lombok.Builder;
import lombok.Data;
import java.util.List;

@Data
@Builder
public class SearchVO {
    private List<ItemVO> list;
    private int total;
    
    @Data
    @Builder
    public static class ItemVO {
        private Long id;
        private String title;
        private String price;
    }
}
