package com.fleafair.VO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserVO {
    private Long userId;
    private String username;
    //返回头像url,默认返回默认头像
    private String avatar;

}
