package com.blackcat.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName(value = "address_info")
public class AddressInfo {
    @TableId(type = IdType.AUTO)
    private Long addressId; // 地址ID，自增主键

    private Integer userId; // 关联用户表user_id
    private String receiverName; // 收件人姓名
    private String receiverPhone; // 收件人手机号
    private String province; // 省份
    private String city; // 城市
    private String district; // 区县
    private String detailAddress; // 详细地址
    private Integer isDefault; // 是否默认地址：0-否，1-是
}