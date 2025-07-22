package com.fleafair.VO;

import lombok.Builder;
import lombok.Data;
import org.springframework.boot.autoconfigure.elasticsearch.ElasticsearchConnectionDetails;

@Builder
@Data
public class UserVO {
    private String username;
    //返回头像url,默认返回默认头像
    private String avatar;

}
