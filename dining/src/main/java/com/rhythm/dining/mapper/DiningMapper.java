package com.rhythm.dining.mapper;

import com.rhythm.common.Enum.UserLevel;
import com.rhythm.dining.entity.Dining;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
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

    @Insert("insert into user_dining values(#{userId, #{diningId})")
    void addRelation(@Param("userId") Integer userId, @Param("diningId") Integer diningId);

    @Delete("delete from user_dining where userId = #{userId} and diningId = #{diningId}")
    void delRelation(@Param("userId") Integer userId, @Param("diningId") Integer diningId);

    @Select("select username from user where id in (select userId from user_dining where diningId = #{diningId}) and level = 2")
    String getManageName(@Param("diningId") Integer diningId);

    @Select("select * from dining where id in (select diningId from user_dining where userId = #{userId})")
    List<Dining> getDiningByUserId(@Param("userId") Integer userId);
}
