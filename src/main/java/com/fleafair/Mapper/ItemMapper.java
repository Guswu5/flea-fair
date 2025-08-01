package com.fleafair.Mapper;

import com.fleafair.Entity.Item;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

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


    /**
     * 根据id查询商品
     * @param id
     * @return
     */
    @Select("select * from item where id = #{id}")
    Item getById(Long id);

    /**
     * 统计商品数量
     * @param keyword
     * @return
     */
    @Select("select count(*) from item where name like concat('%', #{keyword}, '%')")
    int countItems(String keyword);

    /**
     * 获取默认商品
     * @return
     */
    List<Item> getDefaultItems();
}
