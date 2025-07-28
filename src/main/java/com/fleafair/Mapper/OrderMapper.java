package com.fleafair.Mapper;

import com.fleafair.Entity.Order;
import com.fleafair.Entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface OrderMapper {

    /**
     * 插入订单
     * @param order
     */
    @Insert("insert into orders" +
            "(item_id, address_id, status, create_time, update_time) " +
            "values(#{itemId}, #{addressId}, #{status}, #{createTime}, #{updateTime})")
    void insert(Order order);
}