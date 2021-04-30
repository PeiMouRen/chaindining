package com.rhythm.order.service.inter;

import com.rhythm.common.result.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "user")
public interface IUserService {

    @GetMapping(value = "/user/user/{id}")
    Result getUser(@PathVariable Integer id);

}
