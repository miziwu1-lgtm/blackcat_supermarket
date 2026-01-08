package com.blackcat.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.blackcat.VO.OrderAmountByTimeVO;
import com.blackcat.VO.OrderDetailsVO;
import com.blackcat.VO.StatusVO;
import com.blackcat.entity.OrderMaster;
import com.blackcat.utils.R;
import com.blackcat.services.IOrderMasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("orderMaster")
public class OrderMasterController {
    @Autowired
    IOrderMasterService orderMasterService;
    //管理端
    //编辑订单
    @PutMapping
    public R update(@RequestBody OrderMaster orderMaster) {
        return new R(orderMasterService.updateById(orderMaster));
    }
    //管理端
    //分页条件查询
    @GetMapping("/{currentpage}/{pagesize}")
    public R getPage(
            @PathVariable Integer currentpage,
            @PathVariable Integer pagesize,
            // 对应前端 params 中的 orderId
            @RequestParam(required = false) Long orderId,
            // 对应前端 params 中的 userId
            @RequestParam(required = false) Long userId,
            // 对应前端 params 中的 keyword (搜索收件人姓名或电话)
            @RequestParam(required = false) String keyword,
            // 对应前端 params 中的 orderStatus
            @RequestParam(required = false) Integer orderStatus,
            // 对应前端 params 中的 payStatus
            @RequestParam(required = false) Integer payStatus) {

        // 1. 创建分页对象
        IPage<OrderMaster> page = new Page<>(currentpage, pagesize);

        // 2. 构造查询条件
        LambdaQueryWrapper<OrderMaster> wrapper = new LambdaQueryWrapper<>();

        // 条件 A：精准匹配 订单ID (orderId)
        wrapper.eq(orderId != null, OrderMaster::getOrderId, orderId);

        // 条件 B：精准匹配 用户ID (userId)
        wrapper.eq(userId != null, OrderMaster::getUserId, userId);

        // 条件 C：模糊匹配收件人姓名或手机号 (对应前端的 keyword)
        if (keyword != null && !keyword.isEmpty()) {
            wrapper.and(w -> w.like(OrderMaster::getReceiverName, keyword)
                    .or()
                    .like(OrderMaster::getReceiverPhone, keyword));
        }

        // 条件 D：订单状态过滤 (0-待付款, 1-待发货等)
        wrapper.eq(orderStatus != null, OrderMaster::getOrderStatus, orderStatus);

        // 条件 E：支付状态过滤 (0-未支付, 1-已支付等)
        wrapper.eq(payStatus != null, OrderMaster::getPayStatus, payStatus);

        // 排序：按创建时间倒序排（最新的订单在最上面）
        wrapper.orderByDesc(OrderMaster::getCreateTime);

        // 3. 执行分页查询
        orderMasterService.page(page, wrapper);

        // 4. 返回结果
        return new R(true, page,"查询成功");
    }
    //管理端
    //订单详细显示
    @GetMapping("/detail/{id}")
    public R getDetail(@PathVariable Long id) {
        OrderDetailsVO orderVO = orderMasterService.getOrderFullDetail(id);
        if (orderVO != null) {
            return new R(true, orderVO,"查询成功");
        } else {
            return new R(false, null,"订单不存在");
        }
    }

    //管理端
    //订单状态分布图
    @GetMapping("/status")
    public R GetStatus(){
        List<StatusVO> data = orderMasterService.countStatus();
        return new R(true,data,"查询成功");
    }
    //管理端
    //订单时间分布总概览
    @GetMapping("/trend")
    public R getTrend(@RequestParam String time){
        // 直接把前端传来的参数传给 Service
        List<OrderAmountByTimeVO> data = orderMasterService.countOrderAmountByTime(time);
        return new R(true, data,"查询成功");
    }
    //管理端
    //销售额概览
    @GetMapping("/salesOverview")
    public R getSalesOverview(){
        return new R(true,orderMasterService.salesOverview(),"查询成功");
    }
    //管理端
    //订单数概览
    @GetMapping("/ordersOverview")
    public R getOrdersOverview(){
    return new R(true,orderMasterService.ordersOverview(),"查询成功");
    }
}