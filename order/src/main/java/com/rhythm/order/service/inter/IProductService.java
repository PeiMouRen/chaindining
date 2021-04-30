package com.rhythm.order.service.inter;

import com.rhythm.common.result.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "product")
public interface IProductService {

    @GetMapping(value = "/product/product/{id}")
    Result getProduct(@PathVariable Integer id);

}
