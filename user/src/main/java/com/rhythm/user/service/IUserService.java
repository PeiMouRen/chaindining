package com.rhythm.user.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rhythm.user.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.HashSet;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xzpei
 * @since 2021-04-25
 */
public interface IUserService extends IService<User> {

    HashSet<String> getRoles(Integer userId);

    void addUser(User user);

    void updUser(User user);

    Page getUsers(Page<User> page);

}
