package com.rhythm.product.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rhythm.product.entity.Product;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author xzpei
 * @since 2021-04-15
 */
@Repository
public interface ProductMapper extends BaseMapper<Product> {

    void updateInventory(@Param("rpstId") Integer rpstId, @Param("productId") Integer productId,
                         @Param("productNum") Integer productNum, @Param("operate") Integer operate);

    Page selectInventory(@Param("page")Page page, @Param("rpstId") Integer rpstId);

    @Select("select * from rpst_product where rpstId = #{rpstId} and productId = #{productId}")
    Map<String, Integer> selectInventory(@Param("rpstId") Integer rpstId, @Param("productId") Integer productId);

    @Insert("insert into rpst_product value(#{rpstId}, #{productId}, #{productNum})")
    void insertInventory(@Param("rpstId") Integer rpstId, @Param("productId") Integer productId,
                         @Param("productNum") Integer productNum);

}
