package com.blackcat.VO;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.blackcat.entity.AuditManagement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

// 关键1：添加 @TableName，指定主表（audit_management）
@TableName(value = "audit_management")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuditManagementVO extends AuditManagement {
    @TableField(exist = false)
    private String productName;          // 商品名称
    @TableField(exist = false)
    private BigDecimal productPrice;     // 商品价格
    @TableField(exist = false)
    private Integer productStock;        // 商品库存
    @TableField(exist = false)
    private String productDesc;          // 商品描述
    @TableField(exist = false)
    private String productImg;           // 商品图片
    @TableField(exist = false)
    private Integer productShelfStatus;  // 商品上架状态
    @TableField(exist = false)
    private String auditorName;          // 审核人姓名
    @TableField(exist = false)
    private String rejectReason;         // 驳回原因
    @TableField(exist = false)
    private Date auditEndTime;           // 审核完成时间
}