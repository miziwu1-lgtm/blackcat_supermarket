package com.blackcat.VO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategorySalesVO {
    private String categoryName;
    private BigDecimal salesAmount;
}

