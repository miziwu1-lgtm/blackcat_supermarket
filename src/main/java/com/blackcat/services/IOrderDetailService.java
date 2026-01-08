package com.blackcat.services;

import com.baomidou.mybatisplus.extension.service.IService;
import com.blackcat.entity.OrderDetail;

import java.util.List;

public interface IOrderDetailService extends IService<OrderDetail> {
    List<OrderDetail> getByOrderId(Long orderId);
}