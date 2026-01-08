package com.blackcat.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.math.BigDecimal;
@Data
@TableName(value = "shopping_cart")
public class ShoppingCart {
    @TableId(type = IdType.AUTO)
    private Integer cartId; // 购物车项ID，自增主键
    private Integer userId; // 关联用户表
    private String productId; // 关联商品表
    private BigDecimal productPrice; // 商品价格（修正：Double→BigDecimal）
    private Integer buyNum; // 购买数量
}