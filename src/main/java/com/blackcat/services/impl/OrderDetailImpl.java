package com.blackcat.services.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blackcat.entity.OrderDetail;
import com.blackcat.mapper.OrderDetailMapper;
import com.blackcat.services.IOrderDetailService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderDetailImpl
        extends ServiceImpl<OrderDetailMapper, OrderDetail>
        implements IOrderDetailService {
    @Override
    public List<OrderDetail> getByOrderId(Long orderId) {
        // 这里的逻辑只跟 order_detail 表有关
        return this.list(new LambdaQueryWrapper<OrderDetail>()
                .eq(OrderDetail::getOrderId, orderId));
    }
}