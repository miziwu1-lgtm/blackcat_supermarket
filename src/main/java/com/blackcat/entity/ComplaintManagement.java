package com.blackcat.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.util.Date;

@Data
@TableName(value = "complaint_management")
public class ComplaintManagement {
    @TableId(type = IdType.AUTO)
    private Integer complaintId; // 投诉ID，自增主键
    private Integer userId; // 关联用户表
    private Long orderId; // 关联订单表
    private String productId; // 关联商品表
    private String complaintContent; // 投诉内容
    private Date complaintTime; // 投诉提交时间
    private String handleResult; // 处理结果
    private Date handleTime; // 处理完成时间
}