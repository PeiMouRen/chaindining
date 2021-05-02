package com.rhythm.product.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rhythm.common.entity.Bzorder;
import com.rhythm.common.result.Result;
import com.rhythm.product.Enum.ProductOperate;
import com.rhythm.product.entity.Product;
import com.rhythm.product.mapper.ProductMapper;
import com.rhythm.product.service.IProductService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rhythm.product.service.inter.IDiningService;
import com.rhythm.product.service.inter.IOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xzpei
 * @since 2021-04-15
 */
@Slf4j
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements IProductService {

    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private IDiningService diningService;
    @Autowired
    private IOrderService orderService;

    @Override
    public Result updateInventory(String userName, Integer diningId, Integer productId, Integer productNum, Integer operate, String des) {
        Result result = new Result();
        log.info(diningService.getDining(diningId).toString());
        //Map<String, Object> dining = (Map<String, Object>)(diningService.getDining(diningId).getData());
        int size = Integer.MAX_VALUE;
        Map<String, Integer> map = productMapper.selectInventory1(diningId, productId);
        if (operate == ProductOperate.IN.getOperate()) {
            // 采购先判断库存中有没有，没有的话先插入
            if (map == null) {
                // 没有库存，判断采购的数量是否超标
                if (productNum > size) {
                    result.setCode(201);
                    result.setMessage("采购失败，餐厅容量不足！");
                    return result;
                } else {
                    productMapper.insertInventory(diningId, productId, productNum);
                    generateOrder(userName, diningId, productId, productNum, operate, des);
                    result.setCode(200);
                    return result;
                }
            } else {
                int oldNum = map.get("productNum");
                if (productNum + oldNum > size) {
                    result.setCode(201);
                    result.setMessage("采购失败，餐厅容量不足！");
                    return result;
                } else {
                    productMapper.updateInventory(diningId, productId, productNum, operate);
                    generateOrder(userName, diningId, productId, productNum, operate, des);
                    result.setCode(200);
                    return result;
                }
            }
        } else {
            int oldNum = map.get("productNum");
            if (productNum > oldNum) {
                result.setCode(201);
                result.setMessage("点餐失败，菜品不足！");
                return result;
            } else {
                productMapper.updateInventory(diningId, productId, productNum, operate);
                generateOrder(userName, diningId, productId, productNum, operate, des);
                result.setCode(200);
                return result;
            }
        }
    }

    private Result generateOrder(String userName, Integer diningId, Integer productId, Integer productNum, Integer operate, String des) {
        Bzorder order = new Bzorder();
        order.setType(operate);
        order.setUserName(userName);
        Map<String, String> map = null;
        map = ((List<Map<String, String>>)(diningService.getDining(diningId).getData())).get(0);
        order.setDiningName((String)map.get("name"));
        order.setProductName(productMapper.selectById(productId).getName());
        order.setProductNum(productNum);
        order.setTime(LocalDateTime.now());
        order.setDes(des);
        return orderService.addOrder(order);
    }

    @Override
    public Page getInventory(Page page, Integer diningId) {
        page = productMapper.selectInventory(page, diningId);
        List<Product> records = new ArrayList<>();
        for (Object obj : page.getRecords()) {
            Map<String, Integer> map = (Map<String, Integer>)obj;
            Product product = productMapper.selectById(map.get("productId"));
            product.setProductNum(map.get("productNum"));
            records.add(product);
        }
        page.setRecords(records);
        return page;
    }

    @Override
    public List<String> getProductTypes() {
        return productMapper.getProductTypes();
    }
}
