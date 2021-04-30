package com.rhythm.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rhythm.common.Enum.UserLevel;
import com.rhythm.user.entity.User;
import com.rhythm.user.mapper.UserMapper;
import com.rhythm.user.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
    public void addUser(User user) {
        userMapper.insert(user);
        userMapper.delRelation(user.getId());
        if (user.getDiningId() != null) {
            userMapper.addRelation(user.getId(), user.getDiningId());
        }
    }

    @Override
    public void updUser(User user) {
        userMapper.updateById(user);
        userMapper.delRelation(user.getId());
        if (user.getDiningId() != null) {
            userMapper.addRelation(user.getId(), user.getDiningId());
        }
    }

    @Override
    public Page getUsers(Page<User> page) {
        page = userMapper.selectPage(page, new QueryWrapper<User>().ne("level", UserLevel.ADMIN.getLevel()));
        List<User> users = page.getRecords();
        for (User user : users) {
            user.setDiningId(userMapper.getRelation(user.getId()));
        }
        page.setRecords(users);
        return page;
    }

}
