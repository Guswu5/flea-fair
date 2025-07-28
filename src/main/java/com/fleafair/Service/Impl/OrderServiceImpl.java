package com.fleafair.Service.Impl;

import com.fleafair.DTO.OrderCreateDTO;
import com.fleafair.Entity.Order;
import com.fleafair.Mapper.ItemMapper;
import com.fleafair.Mapper.OrderMapper;
import com.fleafair.Service.OrderService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private ItemMapper itemMapper;


    @Override
    public Long CreateOrder(OrderCreateDTO createDTO) {
        Order order = new Order();
        BeanUtils.copyProperties(createDTO, order);

        order.setStatus(Order.PENDING_PAYMENT);
        order.setCreateTime(LocalDateTime.now());
        order.setUpdateTime(LocalDateTime.now());

        orderMapper.insert(order);
        Long orderId = order.getId();
        return orderId;
    }
}
