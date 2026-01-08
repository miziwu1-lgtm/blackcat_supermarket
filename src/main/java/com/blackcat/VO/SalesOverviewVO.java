package com.blackcat.VO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SalesOverviewVO {
    private BigDecimal currentMonthSales; // 本月销售额
    private BigDecimal lastMonthSales;
}
