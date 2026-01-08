package com.blackcat.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.blackcat.VO.ProductVO;
import com.blackcat.entity.ProductInfo;
import com.blackcat.utils.R;
import com.blackcat.services.IProductInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("productInfo")
public class ProductInfoController {
    @Autowired
    IProductInfoService productInfoService;
    private static final SimpleDateFormat DATE_FORMATTER = new SimpleDateFormat("yyyyMMddHHmmss");
    //新增商品
    @PostMapping
    public R save(@RequestBody ProductInfo productInfo) {
        String EndId = String.valueOf(productInfo.getCategoryId());
        if(productInfo.getCategoryId()<10){
            EndId = "00"+EndId;
        }else if(productInfo.getCategoryId()<100&&10<productInfo.getCategoryId()){
            EndId = "0"+EndId;
        }

        String timeStr = DATE_FORMATTER.format(new Date());
        String productId = timeStr + EndId;
        productInfo.setProductId(productId);

        return new R(productInfoService.save(productInfo));
    }
    //编辑商品信息
    @PutMapping
    public R update(@RequestBody ProductInfo productInfo) {
        return new R(productInfoService.updateById(productInfo));
    }
    //删除商品by id
    @DeleteMapping("/{id}")
    public R delete(@PathVariable String id) {
        return new R(true,productInfoService.removeById(id),"删除成功");
    }
    //分页条件显示
    @GetMapping("/{currentpage}/{pagesize}")
    public R getPage(
            @PathVariable Integer currentpage,
            @PathVariable Integer pagesize,
            @RequestParam(required = false) String productId, // 商品ID是String(32)
            @RequestParam(required = false) String keyword,   // 商品名称关键词
            @RequestParam(required = false) Integer categoryId, // 分类筛选
            @RequestParam(required = false) Integer isOnShelf  // 0-下架, 1-上架, 2-待上架
    ) {

        // 1. 创建分页对象 (注意泛型是 ProductVO)
        IPage<ProductVO> page = new Page<>(currentpage, pagesize);

        // 2. 构造查询条件
        QueryWrapper<ProductVO> wrapper = new QueryWrapper<>();
        // 条件 A: 精准匹配商品 ID
        wrapper.eq(productId != null && !productId.isEmpty(), "p.product_id", productId);
        // 条件 B: 模糊匹配商品名称
        wrapper.like(keyword != null && !keyword.isEmpty(), "p.product_name", keyword);
        // 条件 C: 分类筛选
        wrapper.eq(categoryId != null, "p.category_id", categoryId);
        // 条件 D: 上下架状态筛选
        wrapper.eq(isOnShelf != null, "p.is_on_shelf", isOnShelf);
        // 3. 排序：按上架状态倒排，再按价格降序（或者按创建时间）
        wrapper.orderByDesc("p.product_id");


        // 4. 执行自定义联表分页查询
        IPage<ProductVO> resultPage = productInfoService.getProductsCategoryName(page, wrapper);
        return new R(true, resultPage,"查询成功");
    }
    //商品总数概览
    @GetMapping("/productsOverview")
    public R getProductsOverview(){
        return new R(true,productInfoService.productsOverViewVO(),"查询成功");
    }
}