package com.fleafair.Controller;

import com.fleafair.Common.Result;
import com.fleafair.DTO.OrderCreateDTO;
import com.fleafair.Service.OrderService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

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

    /**
     * 支付订单
     * @param paymentMethod
     * @param id
     * @return
     */
    @PostMapping("/{id}/pay")
    public Result<?> PayOrder(String paymentMethod,@PathVariable Long id){
        log.info("支付订单：{}", id);
        if (!orderService.PayOrder(paymentMethod, id)){
            return Result.error("支付失败");
        }

        //TODO: 模拟支付成功
        Map<String,String> result = Map.of(
                "payUrl", id.toString(),
                "paymentMethod", paymentMethod
        );
        return Result.success(result);
    }

    /**
     * 取消订单
     * @param id
     * @return
     */
    @PutMapping("/{id}/cancel")
    public Result<?> CancelOrder(@PathVariable Long id){
        String result = orderService.CancelOrder(id);
        return Result.success(result);
    }

    /**
     * 查询订单
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Result<?> GetOrder(@PathVariable Long id){
        return orderService.GetOrder(id);
    }
}
