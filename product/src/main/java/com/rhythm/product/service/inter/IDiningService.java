package com.rhythm.product.service.inter;

import com.rhythm.common.result.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "dining")
public interface IDiningService {

    @GetMapping(value = "/dining/dining/{diningId}")
    Result getDining(@PathVariable Integer diningId);
}
