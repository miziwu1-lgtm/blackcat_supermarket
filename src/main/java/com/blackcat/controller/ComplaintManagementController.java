package com.blackcat.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.blackcat.VO.ComplaintVO;
import com.blackcat.entity.ComplaintManagement;
import com.blackcat.utils.R;
import com.blackcat.services.IComplaintManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("complaintManagement")
public class ComplaintManagementController {
    @Autowired
    IComplaintManagementService  complaintManagementService;
    //分页条件查询
    @GetMapping("/{currentpage}/{pagesize}")
    public R getPage(
            @PathVariable Integer currentpage,
            @PathVariable Integer pagesize,
            @RequestParam(required = false) Long orderId,
            @RequestParam(required = false) Long userId,
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) Integer status) {

        // 注意：这里的分页对象类型改为 ComplaintVO
        IPage<ComplaintVO> page = new Page<>(currentpage, pagesize);
        LambdaQueryWrapper<ComplaintVO> wrapper = new LambdaQueryWrapper<>();

        // 过滤条件
        wrapper.eq(orderId != null, ComplaintVO::getOrderId, orderId);
        wrapper.eq(userId != null, ComplaintVO::getUserId, userId);

        // 之前的状态逻辑：0-待处理(handleTime为空)，1-已处理(handleTime不为空)
        if (status != null) {
            if (status == 0) wrapper.isNull(ComplaintVO::getHandleTime);
            else if (status == 1) wrapper.isNotNull(ComplaintVO::getHandleTime);
        }

        if (keyword != null && !keyword.isEmpty()) {
            wrapper.like(ComplaintVO::getComplaintContent, keyword);
        }
        if (keyword != null && !keyword.isEmpty()) {
            wrapper.and(w -> w.like(ComplaintVO::getUserNickname, keyword)
                    .or()
                    .like(ComplaintVO::getUserPhone, keyword));
        }

        wrapper.orderByDesc(ComplaintVO::getComplaintTime);

        // 执行联表分页查询
        return new R(true, complaintManagementService.getComplaintPage(page, wrapper),"查询成功");
    }
    //处理投诉更新
    @PutMapping
    public R update(@RequestBody ComplaintManagement complaintManagement) {
        if (complaintManagement.getHandleResult() != null && !complaintManagement.getHandleResult().isEmpty()) {
            // 自动设置当前系统时间为处理完成时间
            complaintManagement.setHandleTime(new java.util.Date());
        }
        return new R(complaintManagementService.updateById(complaintManagement));
    }
}