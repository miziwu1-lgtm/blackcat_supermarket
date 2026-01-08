package com.blackcat.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.util.Date;

@Data
@TableName(value = "admin_info")
public class AdminInfo {
    @TableId(type = IdType.AUTO)
    private Integer adminId; // 管理员ID，自增主键
    private String adminUsername; // 登录账号（唯一）（修正：adminAccount→adminUsername）
    private String adminPassword; // 登录密码（加密存储）
    private String adminName; // 管理员姓名
    private Integer status; // 账号状态：0-禁用，1-正常
}