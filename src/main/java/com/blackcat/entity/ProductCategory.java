package com.blackcat.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName(value = "product_category")
public class ProductCategory {
    @TableId(type = IdType.AUTO)
    private Integer categoryId; // 分类ID，自增主键
    private String categoryName; // 分类名称
    private Integer parentId; // 父分类ID（多级分类用）
}