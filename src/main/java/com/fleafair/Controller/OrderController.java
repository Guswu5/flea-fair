package com.fleafair.Controller;

import com.fleafair.Common.Result;
import com.fleafair.DTO.OrderCreateDTO;
import com.fleafair.Service.OrderService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
@Tag(name = "订单接口")
@Slf4j
public class OrderController {

    @Autowired
    private OrderService orderService;

    /**
     * 创建订单
     * @param createDTO
     * @return
     */
    @PostMapping
    public Result<?> CreateOrder(@RequestBody OrderCreateDTO createDTO){
        log.info("创建订单：{}", createDTO);
        Long orderId = orderService.CreateOrder(createDTO);
        return Result.success(orderId);
    }
}
