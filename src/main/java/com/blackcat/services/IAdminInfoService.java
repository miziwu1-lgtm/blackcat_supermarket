package com.blackcat.services;

import com.baomidou.mybatisplus.extension.service.IService;
import com.blackcat.entity.AdminInfo;
import com.blackcat.utils.R;

public interface IAdminInfoService extends IService<AdminInfo> {
    R login(AdminInfo adminParam);
}