package com.fleafair.Service;

import com.fleafair.Common.Result;
import com.fleafair.DTO.ItemReleaseDTO;

public interface ItemService {

    /**
     * 发布商品
     * @param releaseDTO
     * @return
     */
    Result<?> Release(ItemReleaseDTO releaseDTO, Long userId);

    /**
     * 搜索商品
     * @param keyword
     * @param page
     * @param size
     * @return
     */
    Result<?> SearchItems(String keyword, int page, int size);
}
