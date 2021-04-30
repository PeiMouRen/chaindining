package com.rhythm.user.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rhythm.common.result.Result;
import com.rhythm.user.service.inter.IOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "/order")
public class OrderController {

    @Autowired
    private IOrderService orderService;

    @GetMapping(value = "/order")
    public Result getOrder(Page page) {
        return orderService.getOrders(page);
    }
}
