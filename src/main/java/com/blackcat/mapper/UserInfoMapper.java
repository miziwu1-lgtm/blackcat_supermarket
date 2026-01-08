package com.blackcat.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.blackcat.VO.UsersOverViewVO;
import com.blackcat.entity.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserInfoMapper extends BaseMapper<UserInfo> {
    @Select("SELECT " +
            // 1. 当前所有用户总数
            "(SELECT COUNT(*) FROM user_info) as currentMonthUsers, " +

            // 2. 本月1号0点之前的用户总数 (即上个月底的存量)
            "(SELECT COUNT(*) FROM user_info WHERE addtime < DATE_FORMAT(NOW(), '%Y-%m-01')) as lastMonthUsers")
    UsersOverViewVO getUserOverview();
}