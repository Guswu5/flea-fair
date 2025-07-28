package com.fleafair.Service.Impl;

import com.fleafair.Common.Result;
import com.fleafair.DTO.OrderCreateDTO;
import com.fleafair.DTO.OrderUpdateDTO;
import com.fleafair.Entity.Order;
import com.fleafair.Mapper.ItemMapper;
import com.fleafair.Mapper.OrderMapper;
import com.fleafair.Service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Slf4j
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

    @Override
    public boolean PayOrder(String paymentMethod, Long id) {
        if (paymentMethod == null){
            return false;
        }
        // 更新订单状态
        OrderUpdateDTO orderUpdateDTO = new OrderUpdateDTO();
        orderUpdateDTO.setId(id);
        orderUpdateDTO.setStatus(Order.PAID);
        orderUpdateDTO.setUpdateTime(LocalDateTime.now());

        orderMapper.update(orderUpdateDTO);
        return true;
    }

    @Override
    public String CancelOrder(Long id) {
        OrderUpdateDTO orderCancel = new OrderUpdateDTO();

        // 更新订单状态
        orderCancel.setId(id);
        orderCancel.setStatus(Order.CANCELLED);
        orderCancel.setUpdateTime(LocalDateTime.now());

        orderMapper.update(orderCancel);

        log.info("取消订单：{}", id);
        return "订单已取消";
    }
}
