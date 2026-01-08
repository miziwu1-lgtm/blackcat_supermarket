package com.blackcat.dao;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.blackcat.VO.ProductVO;
import com.blackcat.VO.ProductsOverViewVO;
import com.blackcat.entity.ProductInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ProductInfoMapper extends BaseMapper<ProductInfo> {
    @Select("SELECT " +
            // 本月商品销量：判断主表时间，累加明细表的数量
            "IFNULL(SUM(CASE WHEN DATE_FORMAT(m.create_time, '%Y-%m') = DATE_FORMAT(NOW(), '%Y-%m') THEN d.product_num ELSE 0 END), 0) as currentMonthProducts, " +

            // 上月商品销量
            "IFNULL(SUM(CASE WHEN DATE_FORMAT(m.create_time, '%Y-%m') = DATE_FORMAT(DATE_SUB(NOW(), INTERVAL 1 MONTH), '%Y-%m') THEN d.product_num ELSE 0 END), 0) as lastMonthProducts " +

            "FROM order_master m " +
            "LEFT JOIN order_detail d ON m.order_id = d.order_id")
    ProductsOverViewVO getProductsOverview();

    @Select("SELECT p.*, c.category_name AS categoryName " +
            "FROM product_info p " +
            "LEFT JOIN product_category c ON p.category_id = c.category_id " +
            "${ew.customSqlSegment}")
    IPage<ProductVO> getProductVOPage(IPage<ProductVO> page, @Param(Constants.WRAPPER) Wrapper<ProductVO> wrapper);
}