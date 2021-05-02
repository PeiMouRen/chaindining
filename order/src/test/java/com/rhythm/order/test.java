package com.rhythm.order;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rhythm.common.result.Result;
import com.rhythm.order.service.IBzorderService;
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
    private IBzorderService iBzorderService;

    @Test
    public void test1() {
        Page page = new Page(1, 10);
        page = iBzorderService.page(page);
        log.info("" + page.getTotal());
        log.info("" + page.getRecords().size());
    }
}
