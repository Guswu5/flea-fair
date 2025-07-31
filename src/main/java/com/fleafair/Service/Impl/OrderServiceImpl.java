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
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private ItemMapper itemMapper;
    
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;


    @Override
    public Long CreateOrder(OrderCreateDTO createDTO) {
        Order order = new Order();
        BeanUtils.copyProperties(createDTO, order);

        order.setStatus(Order.PENDING_PAYMENT);
        order.setCreateTime(LocalDateTime.now());
        order.setUpdateTime(LocalDateTime.now());

        orderMapper.insert(order);
        Long orderId = order.getId();
        
        // 清除相关缓存
        redisTemplate.delete("order:id:" + orderId);
        
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
        
        // 更新缓存中的订单状态
        String cacheKey = "order:id:" + id;
        Order cachedOrder = (Order) redisTemplate.opsForValue().get(cacheKey);
        if (cachedOrder != null) {
            cachedOrder.setStatus(Order.PAID);
            cachedOrder.setUpdateTime(LocalDateTime.now());
            redisTemplate.opsForValue().set(cacheKey, cachedOrder, 30, TimeUnit.MINUTES);
        }

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
        
        // 更新缓存中的订单状态
        String cacheKey = "order:id:" + id;
        Order cachedOrder = (Order) redisTemplate.opsForValue().get(cacheKey);
        if (cachedOrder != null) {
            cachedOrder.setStatus(Order.CANCELLED);
            cachedOrder.setUpdateTime(LocalDateTime.now());
            redisTemplate.opsForValue().set(cacheKey, cachedOrder, 30, TimeUnit.MINUTES);
        }

        log.info("取消订单：{}", id);
        return "订单已取消";
    }

    /**
     * 获取订单
     * @param id
     * @return
     */
    @Override
    public Result<?> GetOrder(Long id) {
        if(id == null){
            return Result.error("订单ID不能为空");
        }
        
        // 构建缓存key
        String cacheKey = "order:id:" + id;
        
        // 尝试从缓存中获取
        Order cachedOrder = (Order) redisTemplate.opsForValue().get(cacheKey);
        if (cachedOrder != null) {
            return Result.success(cachedOrder);
        }
        
        // 从数据库获取订单
        Order order = orderMapper.getById(id);
        
        // 将结果存入缓存，设置过期时间为30分钟
        if (order != null) {
            redisTemplate.opsForValue().set(cacheKey, order, 30, TimeUnit.MINUTES);
        }
        
        return Result.success(order);
    }
}
