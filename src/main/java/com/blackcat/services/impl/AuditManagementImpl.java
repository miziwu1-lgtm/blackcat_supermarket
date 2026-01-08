package com.blackcat.services.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blackcat.VO.AuditManagementVO;
import com.blackcat.mapper.AuditManagementMapper;
import com.blackcat.entity.AuditManagement;
import com.blackcat.services.IAuditManagementService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;

@Service
public class AuditManagementImpl extends ServiceImpl<AuditManagementMapper, AuditManagement>
        implements IAuditManagementService {

    @Override
    public IPage<AuditManagementVO> pageAuditManagement(IPage<AuditManagementVO> page, LambdaQueryWrapper<AuditManagementVO> wrapper) {
        return baseMapper.selectAuditPage(page, wrapper);
    }

    @Override
    public AuditManagementVO getAuditDetailById(Integer auditId) {
        return baseMapper.selectAuditDetailById(auditId);
    }

    // 纯基础类型参数，无任何DTO依赖
    @Override
    public boolean updateAuditStatus(Integer auditId, String newStatus, String rejectReason) {
        // 1. 参数校验（匹配数据库audit_status：0-待审核，1-通过，2-驳回）
        if ("2".equals(newStatus) && !StringUtils.hasText(rejectReason)) {
            throw new IllegalArgumentException("驳回原因不能为空");
        }

        // 2. 转换状态为数字（匹配数据库TINYINT类型）
        Integer status = Integer.parseInt(newStatus);

        // 3. 模拟当前登录管理员ID（实际从上下文/Token获取，匹配admin_info表admin_id）
        Integer auditorId = 1;

        // 4. 构建更新参数（完全匹配audit_management表字段）
        Date auditEndTime = status == 0 ? null : new Date(); // 待审核无需完成时间
        String finalRejectReason = status == 2 ? rejectReason.trim() : null;

        // 5. 执行更新
        int rows = baseMapper.updateAuditStatus(
                auditId,
                status,
                finalRejectReason,
                auditEndTime,
                auditorId
        );

        return rows > 0;
    }
}