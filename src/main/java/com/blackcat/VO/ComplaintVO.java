package com.blackcat.VO;

import com.blackcat.entity.ComplaintManagement;
import lombok.Data;

@Data
public class ComplaintVO extends ComplaintManagement {
    // 这里的字段名要和 SQL 查询出来的别名一致
    private String userNickname;
    private String userPhone;
    private String productName;
}