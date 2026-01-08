package com.blackcat.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.blackcat.VO.AuditManagementVO;
import com.blackcat.services.IAuditManagementService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/audits")
@Validated
public class AuditManagementController {
    private static final Logger log = LoggerFactory.getLogger(AuditManagementController.class);

    @Autowired
    private IAuditManagementService auditManagementService;

    // 分页查询（保留原有）
    @GetMapping("/page/{currentpage}/{pagesize}")
    public ResponseEntity<Map<String, Object>> getPage(
            @PathVariable Integer currentpage,
            @PathVariable Integer pagesize,
            @RequestParam(required = false) String productId,
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) Integer status
    ) {
        try {
            int current = currentpage == null || currentpage < 1 ? 1 : currentpage;
            int size = pagesize == null || pagesize < 1 || pagesize > 100 ? 10 : pagesize;

            Page<AuditManagementVO> page = new Page<>(current, size);
            QueryWrapper<AuditManagementVO> wrapper = new QueryWrapper<>();

            if (productId != null && !productId.trim().isEmpty()) {
                wrapper.eq("am.product_id", productId.trim());
            }
            if (status != null) {
                wrapper.eq("am.audit_status", status);
            }
            if (keyword != null && !keyword.trim().isEmpty()) {
                wrapper.apply("pi.product_name LIKE CONCAT('%', {0}, '%')", keyword.trim());
            }

            IPage<AuditManagementVO> pageResult = auditManagementService.pageAuditManagement(page, wrapper.lambda());

            Map<String, Object> result = new HashMap<>();
            result.put("success", true);
            result.put("message", pageResult.getTotal() == 0 ? "未查询到匹配的审核数据" : "查询成功");

            Map<String, Object> data = new HashMap<>();
            data.put("records", pageResult.getRecords());
            data.put("total", pageResult.getTotal());
            data.put("size", pageResult.getSize());
            data.put("current", pageResult.getCurrent());
            result.put("data", data);

            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            log.error("审核列表查询失败", e);
            Map<String, Object> errorResult = new HashMap<>();
            errorResult.put("success", false);
            errorResult.put("message", "查询失败：" + e.getMessage());
            errorResult.put("data", null);
            return new ResponseEntity<>(errorResult, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // 详情查询（保留原有）
    @GetMapping("/detail/{auditId}")
    public ResponseEntity<Map<String, Object>> getAuditDetail(@PathVariable Integer auditId) {
        if (auditId == null || auditId < 1) {
            Map<String, Object> errorResult = new HashMap<>();
            errorResult.put("success", false);
            errorResult.put("message", "审核ID不能为空");
            errorResult.put("data", null);
            return new ResponseEntity<>(errorResult, HttpStatus.BAD_REQUEST);
        }

        try {
            AuditManagementVO detail = auditManagementService.getAuditDetailById(auditId);
            Map<String, Object> result = new HashMap<>();

            if (detail == null) {
                result.put("success", false);
                result.put("message", "未查询到该审核记录");
                result.put("data", null);
                return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
            }

            result.put("success", true);
            result.put("message", "查询成功");
            result.put("data", detail);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            log.error("审核详情查询失败，auditId：{}", auditId, e);
            Map<String, Object> errorResult = new HashMap<>();
            errorResult.put("success", false);
            errorResult.put("message", "查询失败：" + e.getMessage());
            errorResult.put("data", null);
            return new ResponseEntity<>(errorResult, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // 新增：修改审核状态接口
    @PostMapping("/update-status")
    public ResponseEntity<Map<String, Object>> updateAuditStatus(
            @RequestBody Map<String, Object> paramMap // 接收 JSON 体
    ) {
        // 1. 从 Map 中提取参数并校验
        Integer auditId = null;
        try {
            auditId = Integer.parseInt(paramMap.get("auditId").toString());
        } catch (NullPointerException | NumberFormatException e) {
            // auditId 为空/非数字的校验
            Map<String, Object> errorResult = new HashMap<>();
            errorResult.put("success", false);
            errorResult.put("message", "审核ID不能为空且必须为正整数");
            errorResult.put("data", null);
            return new ResponseEntity<>(errorResult, HttpStatus.BAD_REQUEST);
        }

        if (auditId < 1) {
            Map<String, Object> errorResult = new HashMap<>();
            errorResult.put("success", false);
            errorResult.put("message", "审核ID必须为正整数");
            errorResult.put("data", null);
            return new ResponseEntity<>(errorResult, HttpStatus.BAD_REQUEST);
        }

        // 提取 newStatus
        String newStatus = (String) paramMap.get("newStatus");
        if (newStatus == null || !newStatus.matches("[012]")) {
            Map<String, Object> errorResult = new HashMap<>();
            errorResult.put("success", false);
            errorResult.put("message", "审核状态必须为0(待审核)/1(通过)/2(驳回)");
            errorResult.put("data", null);
            return new ResponseEntity<>(errorResult, HttpStatus.BAD_REQUEST);
        }

        // 提取 rejectReason（可选）
        String rejectReason = (String) paramMap.get("rejectReason");

        // 2. 原有业务逻辑完全不变
        try {
            boolean success = auditManagementService.updateAuditStatus(auditId, newStatus, rejectReason);
            Map<String, Object> result = new HashMap<>();

            if (success) {
                result.put("success", true);
                result.put("message", "审核状态更新成功");
                result.put("data", null);
                return new ResponseEntity<>(result, HttpStatus.OK);
            } else {
                result.put("success", false);
                result.put("message", "审核状态更新失败（审核ID不存在或数据未变更）");
                result.put("data", null);
                return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
            }
        } catch (IllegalArgumentException e) {
            log.error("审核状态更新参数异常，auditId：{}", auditId, e);
            Map<String, Object> errorResult = new HashMap<>();
            errorResult.put("success", false);
            errorResult.put("message", e.getMessage());
            errorResult.put("data", null);
            return new ResponseEntity<>(errorResult, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            log.error("审核状态更新失败，auditId：{}", auditId, e);
            Map<String, Object> errorResult = new HashMap<>();
            errorResult.put("success", false);
            errorResult.put("message", "更新失败：" + e.getMessage());
            errorResult.put("data", null);
            return new ResponseEntity<>(errorResult, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}