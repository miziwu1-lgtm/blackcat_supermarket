package com.blackcat.utils;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.UUID;

@Service
public class OssService {

    @Value("${aliyun.oss.endpoint}")
    private String endpoint;
    @Value("${aliyun.oss.accessKeyId}")
    private String accessKeyId;
    @Value("${aliyun.oss.accessKeySecret}")
    private String accessKeySecret;
    @Value("${aliyun.oss.bucketName}")
    private String bucketName;

    public String uploadFile(MultipartFile file, String folder) {
        // 1. 创建 OSS 客户端实例
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        try {
            // 2. 获取上传文件的输入流
            InputStream inputStream = file.getInputStream();

            // 3. 生成唯一文件名 (防止同名文件覆盖)
            String originalFilename = file.getOriginalFilename();
            String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
//            String fileName = UUID.randomUUID().toString() + suffix;

            String fileName = folder + "/" + UUID.randomUUID().toString() + suffix;
            // 4. 上传文件到指定的 Bucket
            // 参数：Bucket名, 文件名(Object名), 输入流
            ossClient.putObject(bucketName, fileName, inputStream);

            // 5. 拼接图片的 URL 并返回
            // 格式：https://bucket名称.地域节点/文件名
            return "https://" + bucketName + "." + endpoint + "/" + fileName;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            // 6. 关闭客户端，释放连接
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }
    }
}