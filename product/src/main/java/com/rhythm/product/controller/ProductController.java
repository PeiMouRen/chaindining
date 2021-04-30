package com.rhythm.product.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rhythm.common.Enum.UserLevel;
import com.rhythm.common.entity.User;
import com.rhythm.common.result.Result;
import com.rhythm.product.entity.Product;
import com.rhythm.product.service.IProductService;
import com.rhythm.product.service.inter.IRpstService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author xzpei
 * @since 2021-04-15
 */
@Slf4j
@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private IProductService productService;
    @Autowired
    private ObjectMapper objectMapper;

    @GetMapping(value = "/inventory/{rpstId}")
    public Result getInventory(Page page, @PathVariable Integer rpstId) {
        log.info("获取库存信息：");
        log.info("分页详情" + page.toString());
        log.info("仓库id：" + rpstId);
        if (rpstId == -1) {
            return Result.ok();
        }
        page = productService.getInventory(page, rpstId);
        Result result = Result.ok();
        result.setTotal(page.getTotal());
        result.setData(page.getRecords());
        return result;
    }

    @PutMapping(value = "/inventory")
    public Result updateInventory(@RequestBody Map<String, String> param, HttpSession session) {
        log.info("更新库存：");
        log.info(param.toString());
        User user = null;
        try {
            user = objectMapper.readValue((String)session.getAttribute("user"), User.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return productService.updateInventory(user.getUsername(), Integer.parseInt(param.get("rpstId")), Integer.parseInt(param.get("productId")),
                Integer.parseInt(param.get("productNum")), Integer.parseInt(param.get("operate")), param.get("des"));
    }

    @PostMapping(value = "/product")
    public Result addProduct(@RequestBody Product product) {
        if (productService.getOne(new QueryWrapper<Product>().eq("name", product.getName())) != null) {
            Result result = Result.error();
            result.setMessage("新增失败，该产品已存在!");
            return result;
        }
        productService.save(product);
        return Result.ok();
    }

    @DeleteMapping(value = "/product/{id}")
    public Result DeleteProduct(@PathVariable Integer id) {
        productService.removeById(id);
        return Result.ok();
    }

    @PutMapping(value = "/product")
    public Result updateProduct(@RequestBody Product product) {
       productService.updateById(product);
       return Result.ok();
    }

    @GetMapping(value = "/products")
    public Result getProducts(Page page) {
        page = productService.page(page);
        Result result = Result.ok();
        result.setTotal(page.getTotal());
        result.setData(page.getRecords());
        return result;
    }

}
