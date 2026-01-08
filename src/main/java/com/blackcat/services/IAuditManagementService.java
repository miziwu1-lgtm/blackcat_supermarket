package com.blackcat.services;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.blackcat.VO.AuditManagementVO;
import com.blackcat.entity.AuditManagement;

public interface IAuditManagementService extends IService<AuditManagement> {
    // 泛型改回 VO
    IPage<AuditManagementVO> pageAuditManagement(IPage<AuditManagementVO> page, LambdaQueryWrapper<AuditManagementVO> wrapper);
    AuditManagementVO getAuditDetailById(Integer auditId);
    // 修改审核状态
    boolean updateAuditStatus(Integer auditId, String newStatus, String rejectReason);

}

