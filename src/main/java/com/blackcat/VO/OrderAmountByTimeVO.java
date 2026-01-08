package com.blackcat.VO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderAmountByTimeVO {
    private String dateStr;
    private Integer totalOrder;
    private Double totalAmount;
}
