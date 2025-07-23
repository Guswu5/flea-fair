package com.fleafair.Service;

import com.fleafair.Common.Result;
import com.fleafair.DTO.ItemReleaseDTO;
import org.springframework.web.bind.annotation.RequestBody;

public interface ItemService {

    /**
     * 发布商品
     * @param releaseDTO
     * @return
     */
    Result<?> Release(ItemReleaseDTO releaseDTO);
}
