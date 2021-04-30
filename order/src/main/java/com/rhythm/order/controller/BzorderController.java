package com.rhythm.order.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rhythm.common.result.Result;
import com.rhythm.order.entity.Bzorder;
import com.rhythm.order.service.IBzorderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author xzpei
 * @since 2021-04-19
 */
@RestController
@Slf4j
@RequestMapping("/order")
public class BzorderController {
    @Autowired
    private IBzorderService orderService;

    @PutMapping(value = "/order")
    public Result addOrder(@RequestBody Bzorder order) {
        log.info("新增订单：" + order.toString());
        orderService.save(order);
        return Result.ok();
    }

    @GetMapping(value = "/order")
    public Result getOrder(Page page) {
        page = orderService.page(page);
        Result result = Result.ok();
        result.setTotal(page.getTotal());
        result.setData(page.getRecords());
        return result;
    }
}
