package com.rhythm.dining.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rhythm.common.Enum.UserLevel;
import com.rhythm.dining.entity.Dining;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author xzpei
 * @since 2021-04-26
 */
@Repository
public interface DiningMapper extends BaseMapper<Dining> {

    List<Dining> getDiningByUserId(@Param("userId") Integer userId);

    Page getDinings(Page page);

    @Update("update user set diningId = #{diningId} where id = #{userId}")
    void setDining(@Param("userId") Integer userId, @Param("diningId") Integer diningId);

    @Update("update user set diningId = null where diningId = #{diningId} and level = 2")
    void delDiningManager(@Param("diningId") Integer diningId);
}
