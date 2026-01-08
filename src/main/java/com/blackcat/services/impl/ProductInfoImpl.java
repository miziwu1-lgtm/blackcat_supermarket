package com.blackcat.services.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blackcat.VO.ProductVO;
import com.blackcat.VO.ProductsOverViewVO;
import com.blackcat.entity.ProductInfo;
import com.blackcat.dao.ProductInfoMapper;
import com.blackcat.services.IProductInfoService;
import org.springframework.stereotype.Service;

@Service
public class ProductInfoImpl
        extends ServiceImpl<ProductInfoMapper, ProductInfo>
        implements IProductInfoService {
    @Override
    public ProductsOverViewVO productsOverViewVO() {
        return baseMapper.getProductsOverview();
    }
    @Override
    public IPage<ProductVO> getProductsCategoryName(IPage<ProductVO> page, QueryWrapper wrapper) {
        return baseMapper.getProductVOPage(page,wrapper);
    }
}
