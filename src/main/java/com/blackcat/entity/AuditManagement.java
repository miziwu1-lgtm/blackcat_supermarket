package com.blackcat.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.util.Date;

@Data
@TableName(value = "audit_management")
public class AuditManagement {
    @TableId(type = IdType.AUTO)
    private Integer auditId; // 审核ID，自增主键
    private String productId; // 关联商品信息表
    private Integer auditorId; // 审核人ID
    private Integer auditStatus; // 审核状态：0-待审核，1-通过，2-驳回
    private String rejectReason; // 驳回原因
    private Date auditStartTime; // 提交时间
    private Date auditEndTime; // 审核完成时间
}