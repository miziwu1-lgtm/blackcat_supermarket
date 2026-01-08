package com.blackcat.services.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blackcat.VO.ComplaintVO;
import com.blackcat.entity.ComplaintManagement;
import com.blackcat.dao.ComplaintManagementMapper;
import com.blackcat.services.IComplaintManagementService;
import org.springframework.stereotype.Service;

@Service
public class ComplaintManagementImpl
        extends ServiceImpl<ComplaintManagementMapper, ComplaintManagement>
        implements IComplaintManagementService {
    @Override
    public IPage<ComplaintVO> getComplaintPage(IPage<ComplaintVO> page, LambdaQueryWrapper wrapper) {
        return baseMapper.getComplaintWithUserPage(page, wrapper);
    }
}