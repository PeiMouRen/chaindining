package com.rhythm.dining.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rhythm.dining.entity.Dining;
import com.rhythm.dining.mapper.DiningMapper;
import com.rhythm.dining.service.IDiningService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xzpei
 * @since 2021-04-26
 */
@Service
public class DiningServiceImpl extends ServiceImpl<DiningMapper, Dining> implements IDiningService {

    @Autowired
    private DiningMapper diningMapper;

    @Override
    public void addDining(Dining dining) {
        diningMapper.insert(dining);
    }

    @Override
    public void updDining(Dining dining) {
        diningMapper.updateById(dining);
    }

    @Override
    public Page getDinings(Page page) {
        page = diningMapper.selectPage(page, new QueryWrapper<>());
        return page;
    }
}
