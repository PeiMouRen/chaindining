package com.rhythm.user.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rhythm.user.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author xzpei
 * @since 2021-04-25
 */
@Repository
public interface UserMapper extends BaseMapper<User> {

    @Select("select r.name from role r where r.id in(select roleId from user_role where userId = #{userId})")
    HashSet<String> getRoles(@Param("userId") Integer userId);

    @Insert("insert into user_role values(#{userId}, #{roleId})")
    void addRole(@Param("userId") Integer userId, @Param("roleId") Integer roleId);

    Page getUsersByDiningId(Page page, Integer diningId);

    @Update("update user set diningId = #{diningId} where id = #{userId}")
    void setDining(@Param("userId") Integer userId, @Param("diningId") Integer diningId);

    @Update("update user set diningId = null where diningId = #{diningId} and level = 2")
    void delDiningManager(@Param("diningId") Integer diningId);
}
