package com.rhythm.dining.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rhythm.dining.entity.Dining;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xzpei
 * @since 2021-04-26
 */
public interface IDiningService extends IService<Dining> {

    void addDining(Dining dining);

    void updDining(Dining dining);

    Page getDinings(Page page);
}
