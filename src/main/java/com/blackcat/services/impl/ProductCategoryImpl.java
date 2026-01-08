package com.blackcat.services.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blackcat.VO.CategorySalesVO;
import com.blackcat.entity.ProductCategory;
import com.blackcat.mapper.ProductCategoryMapper;
import com.blackcat.services.IProductCategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductCategoryImpl
        extends ServiceImpl<ProductCategoryMapper, ProductCategory>
        implements IProductCategoryService {
    @Override
    public List<CategorySalesVO> getTop5CategorySales() {
        return baseMapper.getTop5CategorySales();
    }

}