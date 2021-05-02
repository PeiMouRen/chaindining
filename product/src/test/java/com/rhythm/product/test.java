package com.rhythm.product;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rhythm.common.entity.Dining;
import com.rhythm.common.result.Result;
import com.rhythm.product.mapper.ProductMapper;
import com.rhythm.product.service.inter.IDiningService;
import com.rhythm.product.service.inter.IUserService;
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
@SpringBootTest(classes = ProductApplication.class)
public class test {

    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private IDiningService diningService;
    @Autowired
    private IUserService userService;

    @Test
    public void test() {
        Map<String, Integer> map = productMapper.selectInventory1(14, 13);
        log.info(map.toString());


    }

    public static void main(String[] args) {
        LocalDateTime a = LocalDateTime.now();
        System.out.println(a);
    }
}
