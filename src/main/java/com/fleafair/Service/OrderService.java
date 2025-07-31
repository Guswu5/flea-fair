package com.fleafair.Service;

import com.fleafair.Common.Result;
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

    /**
     * 取消订单
     * @param id
     * @return
     */
    String CancelOrder(Long id);

    /**
     * 获取订单
     * @param id
     * @return
     */
    Result<?> GetOrder(Long id);
}
