package com.blackcat.services;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.blackcat.VO.ProductVO;
import com.blackcat.VO.ProductsOverViewVO;
import com.blackcat.entity.ProductInfo;

public interface IProductInfoService extends IService<ProductInfo> {
    ProductsOverViewVO productsOverViewVO();
    IPage<ProductVO> getProductsCategoryName(IPage<ProductVO> page, QueryWrapper wrapper);
}