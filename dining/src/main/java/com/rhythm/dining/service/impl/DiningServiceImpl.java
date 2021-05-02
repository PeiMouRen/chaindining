package com.rhythm.dining.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rhythm.dining.entity.Dining;
import com.rhythm.dining.mapper.DiningMapper;
import com.rhythm.dining.service.IDiningService;
import com.rhythm.dining.service.inter.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xzpei
 * @since 2021-04-26
 */
@Service
@Slf4j
public class DiningServiceImpl extends ServiceImpl<DiningMapper, Dining> implements IDiningService {

    @Autowired
    private DiningMapper diningMapper;
    @Autowired
    private IUserService userService;

    @Override
    public void addDining(Dining dining) {
        diningMapper.insert(dining);
        if (dining.getUserId() != null) {
            diningMapper.setDining(dining.getUserId(), dining.getId());
        }
    }

    @Override
    public void updDining(Dining dining) {
        diningMapper.updateById(dining);
        diningMapper.delDiningManager(dining.getId());
        if (dining.getUserId() != null) {
            diningMapper.setDining(dining.getUserId(), dining.getId());
        }
    }

    @Override
    public List<Dining> getDiningByManageId(Integer userId) {
        List<Dining> dinings = diningMapper.getDiningByUserId(userId);
        return dinings;
    }

    @Override
    public Page getDinings(Page page) {
        return diningMapper.getDinings(page);
    }
}
