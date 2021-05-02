package com.rhythm.product.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rhythm.common.result.Result;
import com.rhythm.product.entity.Product;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xzpei
 * @since 2021-04-15
 */
public interface IProductService extends IService<Product> {
    Result updateInventory(String userName, Integer diningId, Integer productId, Integer productNum, Integer operate, String des);

    Page getInventory(Page page, Integer diningId);

    List<String> getProductTypes();
}
