package com.blackcat.services;

import com.baomidou.mybatisplus.extension.service.IService;
import com.blackcat.VO.UsersOverViewVO;
import com.blackcat.entity.UserInfo;

public interface IUserInfoService extends IService<UserInfo> {
    UsersOverViewVO UsersOverViewVO();
}