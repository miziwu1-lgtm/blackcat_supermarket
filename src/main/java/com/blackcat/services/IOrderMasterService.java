package com.blackcat.services;

import com.baomidou.mybatisplus.extension.service.IService;
import com.blackcat.VO.*;
import com.blackcat.entity.OrderMaster;

import java.util.List;

public interface IOrderMasterService extends IService<OrderMaster> {
    List<StatusVO> countStatus();
    List<OrderAmountByTimeVO> countOrderAmountByTime(String time);
    SalesOverviewVO salesOverview();
    OrdersOverViewVO ordersOverview();
    OrderDetailsVO getOrderFullDetail(Long orderId);
}