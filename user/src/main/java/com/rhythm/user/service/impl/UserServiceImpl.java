package com.rhythm.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rhythm.common.Enum.UserLevel;
import com.rhythm.common.result.Result;
import com.rhythm.user.entity.User;
import com.rhythm.user.mapper.UserMapper;
import com.rhythm.user.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sun.org.apache.bcel.internal.generic.DDIV;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xzpei
 * @since 2021-04-25
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public HashSet<String> getRoles(Integer userId) {
        return userMapper.getRoles(userId);
    }

    @Override
    public Result addUser(User user) {
        if (user.getLevel() == 2 && user.getDiningId() !=  null) {
            userMapper.delDiningManager(user.getDiningId());
        }
        userMapper.insert(user);
        userMapper.addRole(user.getId(), user.getLevel());
        return Result.ok();
    }

    @Override
    public void updUser(User user) {
        if (user.getLevel() == 2 && user.getDiningId() !=  null) {
            userMapper.delDiningManager(user.getDiningId());
        }
        userMapper.updateById(user);
    }

    @Override
    public Page getUsers(Page<User> page,Integer mark) {
        if (mark == -1) {
            page = userMapper.selectPage(page, new QueryWrapper<User>().ne("level", UserLevel.ADMIN.getLevel()));
        } else if (mark == -2) {
            page = userMapper.selectPage(page, new QueryWrapper<User>().eq("level", UserLevel.MANAGER.getLevel()));
        } else {
            page = userMapper.getUsersByDiningId(page, mark);
        }
        return page;
    }

    @Override
    public void setDining(Integer userId, Integer diningId) {
        userMapper.setDining(userId, diningId);
    }

    @Override
    public void delDiningManager(Integer diningId) {
        userMapper.delDiningManager(diningId);
    }

}
