package com.blackcat.dao;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.blackcat.VO.AuditManagementVO;
import com.blackcat.entity.AuditManagement;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.Date;

@Mapper
public interface AuditManagementMapper extends BaseMapper<AuditManagement> {

    /**
     * MP分页+关联商品表查询（核心）
     * @param page 分页对象
     * @param wrapper 条件构造器（自动拼接where）
     * @return 包含商品名称的分页结果
     */
    @Select("""
        SELECT 
            am.*, 
            pi.product_name AS productName,
            ai.admin_name AS auditorName
        FROM audit_management am
        LEFT JOIN product_info pi ON am.product_id = pi.product_id
            LEFT JOIN admin_info ai ON am.auditor_id = ai.admin_id
        ${ew.customSqlSegment} 
        ORDER BY am.audit_start_time DESC
        """)
    IPage<AuditManagementVO> selectAuditPage(
            IPage<AuditManagementVO> page,
            @Param(Constants.WRAPPER) Wrapper<AuditManagementVO> wrapper
    );

    /**
     * 审核详情查询（关联商品表+管理员表）
     * @param auditId 审核ID
     * @return 包含商品详情的审核信息
     */
    @Select("""
        SELECT 
            am.*, 
            pi.product_name AS productName,
            pi.price AS productPrice,
            pi.stock AS productStock,
            pi.description AS productDesc,
            pi.img_url AS productImg,
            pi.is_on_shelf AS productShelfStatus,
            ac.admin_name AS auditorName
        FROM audit_management am
        LEFT JOIN product_info pi ON am.product_id = pi.product_id
        LEFT JOIN admin_info ac ON am.auditor_id = ac.admin_id
        WHERE am.audit_id = #{auditId}
        """)
    AuditManagementVO selectAuditDetailById(@Param("auditId") Integer auditId);

    // 新增：修改审核状态
    @Update("UPDATE audit_management " +
            "SET audit_status = #{auditStatus}, " +
            "reject_reason = #{rejectReason}, " +
            "audit_end_time = #{auditEndTime}, " +
            "auditor_id = #{auditorId} " +
            "WHERE audit_id = #{auditId}")
    int updateAuditStatus(@Param("auditId") Integer auditId,
                          @Param("auditStatus") Integer auditStatus,
                          @Param("rejectReason") String rejectReason,
                          @Param("auditEndTime") Date auditEndTime,
                          @Param("auditorId") Integer auditorId);
}
