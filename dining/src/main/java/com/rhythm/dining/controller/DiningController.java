package com.rhythm.dining.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rhythm.common.Enum.UserLevel;
import com.rhythm.common.entity.User;
import com.rhythm.common.result.Result;
import com.rhythm.common.util.CommonUtil;
import com.rhythm.dining.entity.Dining;
import com.rhythm.dining.service.IDiningService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author xzpei
 * @since 2021-04-26
 */
@RestController
@Slf4j
@RequestMapping("/dining")
public class DiningController {
    
    @Autowired
    private IDiningService diningService;
    @Autowired
    private ObjectMapper objectMapper;
    
    @PostMapping(value = "/dining")
    public Result addDining(@RequestBody Dining dining) {
        log.info("新增餐厅，" + dining.toString());
        if (diningService.getOne(new QueryWrapper<Dining>().eq("name", dining.getName())) != null) {
            Result result = Result.error();
            result.setMessage("新增失败，该餐厅已存在！");
            return result;
        }
        diningService.addDining(dining);
        return Result.ok();
    }

    @PutMapping(value = "/dining")
    public Result updateDining(@RequestBody Dining dining) {
        log.info("更新餐厅信息，" + dining.toString());
        diningService.updDining(dining);
        return Result.ok();
    }

    @DeleteMapping(value = "/dining/{id}")
    public Result DeleteDining(@PathVariable Integer id) {
        diningService.removeById(id);
        return Result.ok();
    }

    @GetMapping(value = "/dining/{id}")
    public Result getDining(@PathVariable Integer id) {
        Result result = Result.ok();
        List<Dining> dinings = new ArrayList<>();
        dinings.add(diningService.getById(id));
        result.setData(dinings);
        return result;
    }

    @GetMapping(value = "/dinings")
    public Result dinings(Page page, HttpSession session) {
        User user = null;
        try {
            user = objectMapper.readValue((String)session.getAttribute("user"), User.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (user == null) {
            Result result = Result.error();
            result.setMessage("当前登录用户已失效，请重新登录！");
            return Result.error();
        } else {
            if (UserLevel.ADMIN.getLevel() == user.getLevel()) {
                page = diningService.getDinings(page);
            } else {
                page.setRecords(diningService.getDiningByManageId(user.getId()));
                page.setTotal(1);
            }
        }
        Result result = Result.ok();
        result.setTotal(page.getTotal());
        result.setData(page.getRecords());
        return result;
    }
}
