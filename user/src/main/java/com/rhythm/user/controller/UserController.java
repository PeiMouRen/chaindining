package com.rhythm.user.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rhythm.common.result.Result;
import com.rhythm.common.util.CommonUtil;
import com.rhythm.user.entity.User;
import com.rhythm.user.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private IUserService userService;

    /**
     * 用户登录接口
     * @param user user
     * @param request request
     * @return string
     */
    @PostMapping("/login")
    public Result login(@RequestBody User user, HttpServletRequest request) {
        log.info("用户登录：" + user.toString());
        user.setPassword(CommonUtil.getMD5String(user.getPassword())); // 密码加密

        // 根据用户名和密码创建 Token
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword());
        // 获取 subject 认证主体
        Subject subject = SecurityUtils.getSubject();
        try{
            // 开始认证，这一步会跳到我们自定义的 Realm 中
            subject.login(token);
            // 验证一下shiro存入的session是否在httpsession中可以拿到
            //log.info("验证session域：" + ((User)request.getSession().getAttribute("user")).toString());
            return Result.ok();
        }catch(Exception e){
            e.printStackTrace();
            Result errorResult = Result.error();
            errorResult.setMessage("用户名或密码错误！");
            return errorResult;
        }
    }

    @PostMapping(value = "/user")
    public Result addUser(@RequestBody User user) {
        log.info("新增用户，" + user.toString());
        user.setPassword(CommonUtil.getMD5String("123456"));
        if (userService.getOne(new QueryWrapper<User>().eq("username", user.getUsername())) != null) {
            Result result = Result.error();
            result.setMessage("新增失败，该用户已存在！");
            return result;
        }
        userService.addUser(user);
        return Result.ok();
    }

    @PutMapping(value = "/user")
    public Result updateUser(@RequestBody User user) {
        log.info("更新用户信息，" + user.toString());
        userService.updUser(user);
        return Result.ok();
    }

    @DeleteMapping(value = "/user/{id}")
    public Result DeleteUser(@PathVariable Integer id) {
        userService.removeById(id);
        return Result.ok();
    }

    @GetMapping(value = "/user/{id}")
    public Result getUser(@PathVariable Integer id) {
        Result result = Result.ok();
        List<User> users = new ArrayList<>();
        users.add(userService.getById(id));
        result.setData(users);
        return result;
    }

    @GetMapping(value = "/users")
    public Result users(Page page) {
        page = userService.getUsers(page);
        Result result = Result.ok();
        result.setTotal(page.getTotal());
        result.setData(page.getRecords());
        return result;
    }

}
