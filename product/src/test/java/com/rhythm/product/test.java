package com.rhythm.product;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rhythm.common.result.Result;
import com.rhythm.product.mapper.ProductMapper;
import com.rhythm.product.service.inter.IRpstService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.bag.SynchronizedSortedBag;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Slf4j
/*@RunWith(SpringRunner.class)
@SpringBootTest(classes = ProductApplication.class)*/
public class test {

    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private IRpstService rpstService;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void test() {
        Result result = rpstService.getRpst(1);
        Object obj = result.getData();
        log.info(obj.toString());
        Map<String, Object> map = (Map<String, Object>)obj;
        log.info(map.toString());
        int size = (int)map.get("size");
        //int size = Integer.parseInt(map.get("size"));

    }

    public static void main(String[] args) {
        LocalDateTime a = LocalDateTime.now();
        System.out.println(a);
    }
}
