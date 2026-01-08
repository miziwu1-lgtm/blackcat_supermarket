package com.blackcat.services;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.blackcat.VO.ComplaintVO;
import com.blackcat.entity.ComplaintManagement;

public interface IComplaintManagementService extends IService<ComplaintManagement> {
    IPage<ComplaintVO> getComplaintPage(IPage<ComplaintVO> page, LambdaQueryWrapper wrapper);
}