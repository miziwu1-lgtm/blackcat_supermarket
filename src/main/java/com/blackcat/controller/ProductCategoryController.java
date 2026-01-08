package com.blackcat.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.blackcat.VO.CategorySalesVO;
import com.blackcat.utils.R;
import com.blackcat.services.IProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("productCategory")
public class ProductCategoryController {
    @Autowired
    IProductCategoryService productCategoryService;
    @GetMapping
    //管理端
    //获取商品分类信息
    public R GetAll(){
        return new R(true,productCategoryService.list(),"查询成功");
    }
    //管理端
    //商品前五排行榜
    @GetMapping("top5")
    public R getTop5Sales() {
        List<CategorySalesVO> data = productCategoryService.getTop5CategorySales();
        return new R(true, data,"查询成功");
    }
}