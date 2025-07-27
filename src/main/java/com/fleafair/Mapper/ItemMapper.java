package com.fleafair.Mapper;

import com.fleafair.Entity.Item;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ItemMapper {

    /**
     * 插入商品
     * @param item
     */
    void insert(Item item);

    /**
     * 根据关键字搜索商品
     * @param keyword
     * @param offset
     * @param size
     * @return
     */
    List<Item> searchItems(@Param("keyword") String keyword,
                           @Param("offset") int offset,
                           @Param("size") int size);


}
