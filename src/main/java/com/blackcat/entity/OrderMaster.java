package com.blackcat.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
@TableName(value = "order_master")
public class OrderMaster {
    @TableId(type = IdType.AUTO)
    private Long orderId; // 订单ID，自增主键
    private Integer userId; // 关联用户表
    private String receiverName; // 快照：收件人
    private String receiverPhone; // 快照：电话
    private String receiverAddress; // 快照：完整地址(省市区+详细)
    private BigDecimal orderAmount; // 订单总金额（修正：Double→BigDecimal）
    private Integer payStatus; // 支付状态：0-未支付，1-已支付，2-退款中，3-已退款
    private Integer orderStatus; // 订单状态：0-待付款，1-待发货，2-待收货，3-已完成，4-已取消
    private Date createTime; // 下单时间
    private Date payTime; // 支付时间
    private Date shipTime; // 发货时间
}