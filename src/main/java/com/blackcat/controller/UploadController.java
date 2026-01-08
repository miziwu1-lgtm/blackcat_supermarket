package com.blackcat.controller;

import com.blackcat.utils.OssService;
import com.blackcat.utils.R; // 你的统一返回结果类
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/common")
public class UploadController {
    @Autowired
    private OssService ossService;
    @PostMapping("/upload/goods")
    public R uploadGood(MultipartFile file) {
        if (file.isEmpty()) {
            return new R(false, null, "上传失败：文件为空");
        }

        // 调用 OSS 上传逻辑
        String fileUrl = ossService.uploadFile(file,"goods-img");

        if (fileUrl != null) {
            // 返回 R(flag, data, msg)
            // 此时 fileUrl 是 https://... 开头的云端地址
            return new R(true, fileUrl, "上传到阿里云 OSS 成功");
        } else {
            return new R(false, null, "上传云端失败，请检查控制台报错");
        }
    }@PostMapping("/upload/users")
    public R uploadUser(MultipartFile file) {
        if (file.isEmpty()) {
            return new R(false, null, "上传失败：文件为空");
        }

        // 调用 OSS 上传逻辑
        String fileUrl = ossService.uploadFile(file,"users-img");

        if (fileUrl != null) {
            // 返回 R(flag, data, msg)
            // 此时 fileUrl 是 https://... 开头的云端地址
            return new R(true, fileUrl, "上传到阿里云 OSS 成功");
        } else {
            return new R(false, null, "上传云端失败，请检查控制台报错");
        }
    }
}