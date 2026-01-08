package com.blackcat.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.util.Date;

@Data
@TableName(value = "user_info")
public class UserInfo {
    @TableId(type = IdType.AUTO)
    private Integer id; // 用户ID，自增主键
    private String username; // 用户名（唯一）
    private String nickname; // 用户昵称
    private String phoneNumber; // 手机号（唯一）
    private String avatar; // 用户头像URL
    private String password; // 加密后的密码
    private Date addtime; // 注册时间
    private Integer status; // 账号状态：0-正常，1-已注销
}