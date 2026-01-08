package com.blackcat.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.blackcat.entity.AdminInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface AdminInfoMapper extends BaseMapper<AdminInfo> {
}