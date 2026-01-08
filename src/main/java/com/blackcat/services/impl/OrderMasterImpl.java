package com.blackcat.services.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blackcat.VO.*;
import com.blackcat.entity.OrderDetail;
import com.blackcat.entity.OrderMaster;
import com.blackcat.dao.OrderMasterMapper;
import com.blackcat.services.IOrderDetailService;
import com.blackcat.services.IOrderMasterService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderMasterImpl
        extends ServiceImpl<OrderMasterMapper, OrderMaster>
        implements IOrderMasterService {
    @Override
    public List<StatusVO> countStatus(){
        return baseMapper.getStatusCount();
    }
    @Override
    public List<OrderAmountByTimeVO> countOrderAmountByTime(String time){
        return baseMapper.getOrderAmountByTime(time);
    }
    @Override
    public SalesOverviewVO salesOverview(){
        return baseMapper.getSalesOverview();
    }
    @Override
    public OrdersOverViewVO ordersOverview(){
        return baseMapper.getOrdersOverview();
    }
    // 关键点：注入 OrderDetail 的 Service 而不是 Mapper
    @Autowired
    private IOrderDetailService orderDetailService;

    @Override
    public OrderDetailsVO getOrderFullDetail(Long orderId) {
        // 1. 先调用本类的逻辑获取主表信息
        OrderMaster master = this.getById(orderId);
        if (master == null) return null;

        // 2. 转换成 VO（可以使用 BeanUtils 快速拷贝属性）
        OrderDetailsVO vo = new OrderDetailsVO();
        BeanUtils.copyProperties(master, vo);

        // 3. 调用另一个 Service 的逻辑获取明细信息
        // 这样明细的查询逻辑依然封装在 OrderDetailServiceImpl 中
        List<OrderDetail> details = orderDetailService.getByOrderId(orderId);

        // 4. 组装并返回
        vo.setOrderDetails(details);
        return vo;
    }
}