package com.blackcat.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.math.BigDecimal;
import java.util.Date;

@Data
@TableName(value = "order_detail")
public class OrderDetail {
    @TableId(type = IdType.AUTO)
    private Long itemId; // 明细ID，自增主键
    private String orderId; // 关联订单主表
    private String productId; // 关联商品表
    private Integer productNum; // 购买数量
    private BigDecimal productPrice; // 快照：商品单价（修正：Double→BigDecimal）
    private String productName; // 快照：商品名称
    private String productImg; // 快照：商品图片
    private Date createTime; // 创建时间
}