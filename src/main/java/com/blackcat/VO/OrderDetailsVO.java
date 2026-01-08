package com.blackcat.VO;

import com.blackcat.entity.OrderDetail;
import com.blackcat.entity.OrderMaster;
import lombok.Data;

import java.util.List;

@Data
public class OrderDetailsVO extends OrderMaster {
    // 存放该订单下的所有明细
    private List<OrderDetail> orderDetails;
}