package com.rhythm.dining.service.inter;

import com.rhythm.common.result.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "user")
public interface IUserService {

    @PutMapping(value = "/user/setDining")
    Result setDining(@RequestParam("userId") Integer userId, @RequestParam("diningId")Integer diningId);

    @PutMapping(value = "/user/changeDiningManager")
    Result changeDiningManager(@RequestParam("userId")Integer userId, @RequestParam("diningId")Integer diningId);

}
