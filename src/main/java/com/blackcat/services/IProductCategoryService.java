package com.blackcat.services;

import com.baomidou.mybatisplus.extension.service.IService;
import com.blackcat.VO.CategorySalesVO;
import com.blackcat.entity.ProductCategory;

import java.util.List;

public interface IProductCategoryService extends IService<ProductCategory> {
    List<CategorySalesVO> getTop5CategorySales();
}