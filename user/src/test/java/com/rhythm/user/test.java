package com.rhythm.user;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rhythm.common.Enum.UserLevel;
import com.rhythm.common.result.Result;

import com.rhythm.user.entity.User;
import com.rhythm.user.mapper.UserMapper;
import com.rhythm.user.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = UserApplication.class)
public class test {


    @Autowired
    private UserMapper userMapper;

    @Test
    public void test() {

    }

    public static void main(String[] args) {
        LocalDateTime a = LocalDateTime.now();
        System.out.println(a);
    }
}
