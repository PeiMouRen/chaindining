package com.rhythm.dining;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rhythm.dining.mapper.DiningMapper;
import com.rhythm.dining.service.inter.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashSet;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DiningApplication.class)
public class Test {

    @Autowired
    private DiningMapper diningMapper;
    @Autowired
    private IUserService userService;

    @org.junit.Test
    public void userTest() {
         userService.setDining(2,2);
        log.info("ok!!!!!!!!!!!!!!!!!!!!!");
    }

}
