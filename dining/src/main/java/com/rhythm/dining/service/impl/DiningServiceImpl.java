package com.rhythm.dining.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rhythm.dining.entity.Dining;
import com.rhythm.dining.mapper.DiningMapper;
import com.rhythm.dining.service.IDiningService;
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


    @Override
    public void addDining(Dining dining) {
        diningMapper.insert(dining);
    }

    @Override
    public void updDining(Dining dining) {
        diningMapper.updateById(dining);
    }

    @Override
    public List<Dining> getDiningByManageId(Integer userId) {
        return diningMapper.getDiningByUserId(userId);
    }
}
