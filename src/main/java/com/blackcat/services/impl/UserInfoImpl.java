package com.blackcat.services.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blackcat.VO.UsersOverViewVO;
import com.blackcat.entity.UserInfo;
import com.blackcat.dao.UserInfoMapper;
import com.blackcat.services.IUserInfoService;
import org.springframework.stereotype.Service;

@Service
public class UserInfoImpl
        extends ServiceImpl<UserInfoMapper, UserInfo>
        implements IUserInfoService {
@Override
    public UsersOverViewVO UsersOverViewVO() {
        return baseMapper.getUserOverview();
}
}