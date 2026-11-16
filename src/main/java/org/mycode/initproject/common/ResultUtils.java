package org.mycode.initproject.common;

public class ResultUtils {
    //成功
    public static <T> BaseResponse<T> success(T data) {
        return new BaseResponse<>(200, data, "ok");
    }

    //失败 ErrorCode 已经定义好了错误信息 所以只需要传入错误码就好了
    //预设好的错误码和错误信息
    public static BaseResponse<?> error(ErrorCode errorCode) {
        return new BaseResponse<>(errorCode);
    }

    //自定义 状态码和信息
    public static BaseResponse<?> error(int code, String message) {
        return new BaseResponse<>(code, null, message);
    }

    //使用预设的错误码 自己修改错误信息
    public static BaseResponse<?> error(ErrorCode errorCode,String message){
        return new BaseResponse<>(errorCode.getCode(),null,message );
    }
}
