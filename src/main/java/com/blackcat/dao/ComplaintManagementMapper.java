package com.blackcat.dao;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.blackcat.VO.ComplaintVO;
import com.blackcat.entity.ComplaintManagement;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ComplaintManagementMapper extends BaseMapper<ComplaintManagement> {

    @Select("SELECT c.*, u.nickname AS userNickname, u.phone_number AS userPhone, " +
            "p.product_name AS productName " + // 新增商品名称字段
            "FROM complaint_management c " +
            "LEFT JOIN user_info u ON c.user_id = u.id " +
            "LEFT JOIN product_info p ON c.product_id = p.product_id " + // 关联商品表
            "${ew.customSqlSegment}")
    IPage<ComplaintVO> getComplaintWithUserPage(IPage<ComplaintVO> page, @Param(Constants.WRAPPER) Wrapper wrapper);
}