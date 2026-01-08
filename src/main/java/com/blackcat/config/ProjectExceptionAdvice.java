package com.blackcat.config;

import com.blackcat.utils.R;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理器：拦截所有 Controller 抛出的异常
 */
@RestControllerAdvice
public class ProjectExceptionAdvice {

    /**
     * 1. 【数据库-唯一索引重复】
     * 触发场景：注册已存在的用户名、手机号等。
     */
    @ExceptionHandler(DuplicateKeyException.class)
    public R doDuplicateKeyException(DuplicateKeyException ex) {
        String msg = ex.getMessage();
        // 根据数据库约束名判断具体错误
        if (msg.contains("user_info.username")) return new R(false, null,"账号已存在");
        if (msg.contains("user_info.phone_number")) return new R(false, null,"手机号已被注册");
        if (msg.contains("admin_info.admin_username")) return new R(false, null,"管理员账号已存在");
        if (msg.contains("uk_user_product")) return new R(false, null,"购物车已存在该商品");

        return new R(false, null,"数据重复，请检查后重试");
    }

    /**
     * 2. 【数据库-数据完整性违规】
     * 触发场景：
     * - 输入内容过长（代替了 MysqlDataTruncation）
     * - 必填字段没填
     * - 外键约束（删除有子表关联的数据）
     */
    @ExceptionHandler(DataIntegrityViolationException.class)
    public R doDataIntegrityViolationException(DataIntegrityViolationException ex) {
        String msg = ex.getMessage();

        // 判断是否为数据过长
        if (msg.contains("Data truncation") || msg.contains("too long")) {
            return new R(false, null,"输入内容超过了长度限制，请缩减文字内容");
        }

        // 判断是否为非空约束
        if (msg.contains("cannot be null")) {
            return new R(false, null,"必填项不能为空，请检查输入");
        }

        // 判断是否为外键约束（例如删除有商品的分类）
        if (msg.contains("a foreign key constraint fails")) {
            return new R(false, null,"该记录被其他数据关联，无法删除或修改");
        }

        return new R(false, null,"数据库操作失败，请检查填写内容是否合规");
    }

    /**
     * 3. 【最终兜底异常】
     * 触发场景：程序中所有未被上方捕获的异常（如空指针、逻辑报错等）。
     */
    @ExceptionHandler(Exception.class)
    public R doException(Exception ex, HttpServletRequest request) {
        // 在后台控制台打印详细错误，方便开发者排查
        System.err.println("发现未捕获异常，请求路径：" + request.getRequestURI());
        ex.printStackTrace();

        // 给用户返回一个体面的提示
        return new R(false, null,"服务器繁忙，请稍后再试");
    }
}