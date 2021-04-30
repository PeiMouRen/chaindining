package com.rhythm.product.service.inter;

import com.rhythm.common.entity.Bzorder;
import com.rhythm.common.result.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "order")
public interface IOrderService {

    @PutMapping(value = "/order/order")
    Result addOrder(@RequestBody Bzorder order);
}
