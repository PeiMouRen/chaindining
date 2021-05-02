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

    void updateInventory(@Param("diningId") Integer diningId, @Param("productId") Integer productId,
                         @Param("productNum") Integer productNum, @Param("operate") Integer operate);

    Page selectInventory(@Param("page")Page page, @Param("diningId") Integer diningId);

    @Select("select * from dining_product where diningId = #{diningId} and productId = #{productId}")
    Map<String, Integer> selectInventory1(@Param("diningId") Integer diningId, @Param("productId") Integer productId);

    @Insert("insert into dining_product value(#{diningId}, #{productId}, #{productNum})")
    void insertInventory(@Param("diningId") Integer diningId, @Param("productId") Integer productId,
                         @Param("productNum") Integer productNum);

    @Select("select distinct type from product")
    List<String> getProductTypes();

}
