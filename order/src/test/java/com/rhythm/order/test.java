package com.rhythm.order;

import com.rhythm.common.result.Result;
import com.rhythm.order.service.inter.IRpstService;
import com.rhythm.order.service.inter.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = OrderApplication.class)
public class test {

    @Autowired
    private IUserService userService;
    @Autowired
    private IRpstService rpstService;

    @Test
    public void test1() {
        Map<String, Object> map = null;
        Result result = null;
        result = rpstService.getRpst(1);
        log.info("rpst" + result.toString());
        result = userService.getUser(1);
        log.info("user" + result.toString());
        map = (Map<String, Object>)(result.getData());
        log.info("map: " + map.toString());
    }
}
