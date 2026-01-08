package com.blackcat.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.blackcat.VO.OrderAmountByTimeVO;
import com.blackcat.VO.OrdersOverViewVO;
import com.blackcat.VO.SalesOverviewVO;
import com.blackcat.VO.StatusVO;
import com.blackcat.entity.OrderMaster;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface OrderMasterMapper extends BaseMapper<OrderMaster> {
//    管理端
//    订单状态分布图
    @Select("SELECT " +
            "    order_status AS orderStatus, " + // 显式映射到 VO 的 orderStatus 字段
            "    COUNT(*) AS count " +
            "FROM " +
            "    order_master " +
            "WHERE " +
            "    create_time >= DATE_FORMAT(NOW(), '%Y-%m-01') " +
            "GROUP BY " +
            "    order_status")
    List<StatusVO> getStatusCount();
//    管理端
//    订单量销售额时间总图
    @Select("<script>" +
            "SELECT " +
            "    DATE_FORMAT(create_time, '%Y-%m-%d') AS dateStr, " +
            "    IFNULL(SUM(order_amount), 0) AS totalAmount, " +
            "    COUNT(*) AS totalOrder " +
            "FROM " +
            "    order_master " +
            "WHERE " +
            "    <choose>" +
            "        <when test=\"Time == '近7天'\">" +
            "            create_time >= DATE_SUB(CURDATE(), INTERVAL 6 DAY)" +
            "        </when>" +
            "        <when test=\"Time == '近30天'\">" +
            "            create_time >= DATE_SUB(CURDATE(), INTERVAL 29 DAY)" +
            "        </when>" +
            "        <when test=\"Time == '季度'\">" +
            "            QUARTER(create_time) = QUARTER(NOW()) " +
            "            AND YEAR(create_time) = YEAR(NOW())" +
            "        </when>" +
            "        <otherwise>" +
            "            1 = 0" + // 防御性代码，防止未匹配时查全表
            "        </otherwise>" +
            "    </choose>" +
            "GROUP BY " +
            "    DATE_FORMAT(create_time, '%Y-%m-%d') " +
            "ORDER BY " +
            "    dateStr ASC" +
            "</script>")
    List<OrderAmountByTimeVO> getOrderAmountByTime(@Param("Time") String Time);
//    管理端
//    订单销售总额
    @Select("SELECT " +
            "IFNULL(SUM(CASE WHEN DATE_FORMAT(create_time, '%Y-%m') = DATE_FORMAT(NOW(), '%Y-%m') THEN order_amount ELSE 0 END), 0) as currentMonthSales, " +
            "IFNULL(SUM(CASE WHEN DATE_FORMAT(create_time, '%Y-%m') = DATE_FORMAT(DATE_SUB(NOW(), INTERVAL 1 MONTH), '%Y-%m') THEN order_amount ELSE 0 END), 0) as lastMonthSales " +
            "FROM order_master")
    SalesOverviewVO getSalesOverview();
//    管理端
//    订单数量总数
    @Select("SELECT " +
            // 本月订单数：符合条件加1，否则加0
            "IFNULL(SUM(CASE WHEN DATE_FORMAT(create_time, '%Y-%m') = DATE_FORMAT(NOW(), '%Y-%m') THEN 1 ELSE 0 END), 0) as currentMonthOrders, " +
            // 上月订单数：符合条件加1，否则加0
            "IFNULL(SUM(CASE WHEN DATE_FORMAT(create_time, '%Y-%m') = DATE_FORMAT(DATE_SUB(NOW(), INTERVAL 1 MONTH), '%Y-%m') THEN 1 ELSE 0 END), 0) as lastMonthOrders " +
            "FROM order_master")
    OrdersOverViewVO getOrdersOverview();
}
