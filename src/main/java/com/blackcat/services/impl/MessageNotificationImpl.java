package com.blackcat.services.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blackcat.entity.MessageNotification;
import com.blackcat.mapper.MessageNotificationMapper;
import com.blackcat.services.IMessageNotificationService;
import org.springframework.stereotype.Service;


@Service
public class MessageNotificationImpl
        extends ServiceImpl<MessageNotificationMapper, MessageNotification>
        implements IMessageNotificationService {

}