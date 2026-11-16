package org.mycode.initproject.exception;

import org.mycode.initproject.common.ErrorCode;

public class BusinessException extends RuntimeException {
    private final int code;
//    1.自定义错误码和错误信息
    public BusinessException(int code,String message){
        super(message);
        this.code = code;
    }

//   2.预设的错误码和错误信息
    public BusinessException(ErrorCode errorCode){
            super(errorCode.getMessage());
            this.code = errorCode.getCode();
    }

//    3.自定义错误信息
    public BusinessException(ErrorCode errorCode,String meaasge){
        super(meaasge);
        this.code = errorCode.getCode();
    }

    public int getCode(){
        return this.code;
    }
}


