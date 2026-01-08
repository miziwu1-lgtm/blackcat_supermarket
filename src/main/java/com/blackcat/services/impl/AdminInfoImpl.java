package com.blackcat.services.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blackcat.entity.AdminInfo;
import com.blackcat.utils.R;
import com.blackcat.dao.AdminInfoMapper;
import com.blackcat.services.IAdminInfoService;
import org.springframework.stereotype.Service;

@Service
public class AdminInfoImpl
        extends ServiceImpl<AdminInfoMapper, AdminInfo>
        implements IAdminInfoService {

    @Override
    public R login(AdminInfo adminParam) {
        // 1. 第一步：根据用户名从数据库查询管理员信息
        AdminInfo dbAdmin = this.getOne(new LambdaQueryWrapper<AdminInfo>()
                .eq(AdminInfo::getAdminUsername, adminParam.getAdminUsername()));

        // 2. 第二步：判断账号是否存在
        if (dbAdmin == null) {
            return new R(false, null,"账号不存在");
        }

        // 3. 第三步：判断账号状态（假设数据库里 0 是禁用，1 是正常）
        // 如果你的逻辑是 0 正常，1 禁用，请修改此处的判定条件
        if (dbAdmin.getStatus() != null && dbAdmin.getStatus() == 0) {
            return new R(false, null,"该账号已被禁用");
        }

        // 4. 第四步：明文密码比对
        // 注意：adminParam 是前端传来的，dbAdmin 是数据库里查出来的
        if (!dbAdmin.getAdminPassword().equals(adminParam.getAdminPassword())) {
            return new R(false, null,"密码错误");
        }

        // 5. 登录成功
        // 脱敏处理：不将密码返回给前端，保证一定的安全性
        dbAdmin.setAdminPassword(null);
        return new R(true, dbAdmin,"登录成功");
    }
}