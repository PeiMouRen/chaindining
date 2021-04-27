package com.rhythm.user.service.inter;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rhythm.common.entity.Dining;
import com.rhythm.common.result.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.*;

@FeignClient(value = "dining")
public interface IDiningService {

    @GetMapping(value = "/dining/dinings")
    Result getDinings(@SpringQueryMap Page page);

    @PostMapping(value = "/dining/dining")
    Result addDining(Dining dining);

    @PutMapping(value = "/dining/dining")
    Result updateDining(Dining dining);

    @DeleteMapping(value = "/dining/dining/{id}")
    Result DeleteDining(@PathVariable Integer id);

    @GetMapping(value = "/dining/dining/{id}")
    Result getDining(@PathVariable Integer id);
}
