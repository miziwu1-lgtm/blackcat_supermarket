package com.blackcat.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.util.Date;

@Data
@TableName(value = "message_notification")
public class MessageNotification {
    @TableId(type = IdType.AUTO)
    private Integer messageId; // 消息ID，自增主键
    private Integer userId; // 关联用户表
    private String messageTitle; // 消息标题
    private String messageContent; // 消息内容
    private Date messageTime; // 消息发送时间
    private Integer messageStatus; // 消息状态：0-未读，1-已读
}