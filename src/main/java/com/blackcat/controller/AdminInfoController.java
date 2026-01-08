package com.blackcat.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.blackcat.entity.AdminInfo;
import com.blackcat.utils.R;
import com.blackcat.services.IAdminInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("adminInfo")
public class AdminInfoController {
    @Autowired
    IAdminInfoService adminInfoService;

    //登录验证
    @PostMapping("/login")
    public R login(@RequestBody AdminInfo admin) {
        return new R(true,adminInfoService.login(admin), "登录成功");
    }
}