package com.blackcat.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.blackcat.entity.UserInfo;
import com.blackcat.utils.R;
import com.blackcat.services.IUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("user")
public class UserInfoController {
    @Autowired
    IUserInfoService userInfoService;
    //客户端
    //新增用户
    @PostMapping
    public R save(@RequestBody UserInfo userInfo) {
        return new R(userInfoService.save(userInfo));
    }
    //客户端
    //更新用户信息
    @PutMapping
    public R update(@RequestBody UserInfo userInfo){
        return new R(userInfoService.updateById(userInfo));
    }
    //客户端
    //分页条件查询
    @GetMapping("/{currentpage}/{pagesize}")
    public R getPage(
            @PathVariable Integer currentpage,
            @PathVariable Integer pagesize,
            @RequestParam(required = false) Integer id,
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) Integer status) {

        IPage<UserInfo> page = new Page<>(currentpage, pagesize);

        // 使用 MyBatis Plus 的 LambdaQueryWrapper 进行动态 SQL 拼接
        LambdaQueryWrapper<UserInfo> wrapper = new LambdaQueryWrapper<>();

        // 动态判断：参数不为空时才增加查询条件
        wrapper.eq(id != null, UserInfo::getId, id);

        if (keyword != null && !keyword.isEmpty()) {
            wrapper.and(w -> w.like(UserInfo::getUsername, keyword)
                    .or()
                    .like(UserInfo::getPhoneNumber, keyword));
        }

        wrapper.eq(status != null, UserInfo::getStatus, status);

        // 按时间倒序排列（新注册的在前面）
        wrapper.orderByDesc(UserInfo::getAddtime);

        userInfoService.page(page, wrapper);
        return new R(true, page,"查询成功");
    }
    //客户端
    //用户总数概览
    @GetMapping("/usersOverview")
    public R usersOverview(){
        return new R(true,userInfoService.UsersOverViewVO(),"查询成功");
    }
}
