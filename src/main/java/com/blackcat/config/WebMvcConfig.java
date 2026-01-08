package com.blackcat.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 意思是：凡是访问路径以 /images/ 开头的
        // 都去本地目录 /Users/wuhaotian/project_uploads/ 下找文件
        registry.addResourceHandler("/images/**")
                .addResourceLocations("file:/Users/wuhaotian/project_uploads/");
    }
}