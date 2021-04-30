package com.rhythm.user.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rhythm.common.entity.Dining;
import com.rhythm.common.result.Result;
import com.rhythm.common.util.CommonUtil;
import com.rhythm.user.service.inter.IDiningService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/dining")
public class DiningController {

    @Autowired
    private IDiningService diningService;

    @PostMapping(value = "/dining")
    public Result addDining(@RequestBody Dining dining) {
        log.info("新增餐厅，" + dining.toString());
        return diningService.addDining(dining);
    }

    @PutMapping(value = "/dining")
    public Result updateDining(@RequestBody Dining dining) {
        log.info("更新餐厅信息，" + dining.toString());
        return diningService.updateDining(dining);
    }

    @DeleteMapping(value = "/dining/{id}")
    public Result DeleteDining(@PathVariable Integer id) {
        return diningService.DeleteDining(id);
    }

    @GetMapping(value = "/dining/{id}")
    public Result getDining(@PathVariable Integer id) {
        return diningService.getDining(id);
    }

    @GetMapping(value = "/dinings")
    public Result dinings(Page page) {
        return diningService.getDinings(page);
    }

}
