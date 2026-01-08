package com.blackcat.VO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrdersOverViewVO {
    private Integer currentMonthOrders;
    private Integer lastMonthOrders;
}