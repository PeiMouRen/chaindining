package com.rhythm.user.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rhythm.common.entity.Product;
import com.rhythm.common.result.Result;
import com.rhythm.user.service.inter.IProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping(value = "/product")
public class ProductController {

    @Autowired
    private IProductService productService;

    @GetMapping(value = "/productTypes")
    public Result productTypes() {
        return productService.getproductTypes();
    }

    @PutMapping(value = "/inventory")
    Result updateInventory(@RequestBody Map<String, String> param) {
        log.info("更新库存：");
        log.info(param.toString());
        return productService.updateInventory(param);
    }

    @GetMapping(value = "/inventory/{diningId}")
    Result getInventory(@SpringQueryMap Page page, @PathVariable Integer diningId) {
        return productService.getInventory(page, diningId);
    }

    @PostMapping(value = "/product")
    public Result addProduct(@RequestBody Product product) {
        return productService.addProduct(product);
    }

    @DeleteMapping(value = "/product/{id}")
    public Result DeleteProduct(@PathVariable Integer id) {
        return productService.DeleteProduct(id);
    }

    @PutMapping(value = "/product")
    public Result updateProduct(@RequestBody Product product) {
        return productService.updateProduct(product);
    }

    @GetMapping(value = "/products")
    public Result getProducts(Page page) {
        return productService.getProducts(page);
    }
}
