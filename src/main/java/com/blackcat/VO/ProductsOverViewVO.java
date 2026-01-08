package com.blackcat.VO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductsOverViewVO {
    private Integer currentMonthProducts;
    private Integer lastMonthProducts;
}
