package org.mycode.myminiprogram.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 统一响应对象
 */
@Data
@NoArgsConstructor
public class Result {

    private Integer code;

    private String msg;

    private Object data;

    public Result(int i, String success, Object data) {
    }

    /**
     * 成功响应（带数据）
     */
    public static Result ok(Object data) {
        return new Result(200, "success", data);
    }

    /**
     * 成功响应（无数据）
     */
    public static Result ok() {
        return new Result(200, "success", null);
    }

    /**
     * 失败响应（带消息）
     */
    public static Result fail(String msg) {
        return new Result(400, msg, null);
    }

    /**
     * 失败响应（自定义代码和消息）
     */
    public static Result fail(Integer code, String msg) {
        return new Result(code, msg, null);
    }
}
