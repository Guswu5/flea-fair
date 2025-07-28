package com.fleafair.Service;

import com.fleafair.DTO.OrderCreateDTO;

public interface OrderService {
    /**
     * 创建订单
     * @param createDTO
     * @return
     */
    Long CreateOrder(OrderCreateDTO createDTO);

    /**
     * 支付订单
     * @param paymentMethod
     * @param id
     */
    boolean PayOrder(String paymentMethod, Long id);
}
