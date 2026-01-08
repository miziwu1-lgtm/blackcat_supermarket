package com.blackcat.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.math.BigDecimal;

@Data
@TableName(value = "product_info")
public class ProductInfo {
    @TableId(type = IdType.INPUT) // 商品ID是自定义字符串，非自增
    private String productId; // 商品唯一ID（修正：productid→productId）
    private String productName; // 商品名称（修正：productname→productName）
    private Integer categoryId; // 关联商品分类表的category_id（修正：category→categoryId）
    private BigDecimal price; // 商品价格（修正：Double→BigDecimal，匹配SQL DECIMAL）
    private Integer stock; // 商品库存
    private String description; // 商品描述
    private String imgUrl; // 商品图片URL
    private Integer isOnShelf; // 上架状态：0-下架，1-上架，2-待上架
}