package com.fleafair.Mapper;

import com.fleafair.Entity.Item;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ItemMapper {

    /**
     * 插入商品
     * @param item
     */
    void insert(Item item);
}
