package com.rhythm.user.service.inter;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rhythm.common.result.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "order")
public interface IOrderService {

    @GetMapping(value = "/order/order")
    Result getOrders(@SpringQueryMap Page page);
}
