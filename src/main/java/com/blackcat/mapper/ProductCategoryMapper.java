package com.blackcat.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.blackcat.VO.CategorySalesVO;
import com.blackcat.entity.ProductCategory;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ProductCategoryMapper extends BaseMapper<ProductCategory> {
    @Select("SELECT " +
            "    pc.category_name AS categoryName, " +
            "    COALESCE(SUM(CASE WHEN om.pay_status = 1 THEN od.product_num * od.product_price ELSE 0 END), 0) AS salesAmount " +
            "FROM " +
            "    product_category pc " +
            "LEFT JOIN " +
            "    product_info pi ON pc.category_id = pi.category_id " +
            "LEFT JOIN " +
            "    order_detail od ON pi.product_id = od.product_id " +
            "LEFT JOIN " +
            "    order_master om ON od.order_id = om.order_id " +
            "WHERE " +
            "    (om.pay_status = 1 OR om.pay_status IS NULL) " +
            "GROUP BY " +
            "    pc.category_id, pc.category_name " +
            "ORDER BY " +
            "    salesAmount DESC " +
            "LIMIT 5")

    List<CategorySalesVO> getTop5CategorySales();
}