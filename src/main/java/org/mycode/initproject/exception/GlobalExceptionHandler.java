package org.mycode.initproject.exception;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.mycode.initproject.common.BaseResponse;
import org.mycode.initproject.common.ErrorCode;
import org.mycode.initproject.common.ResultUtils;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice   //让这个类成为全局错误处理器 接收controller抛过来的错误
@Slf4j  //自动生成 log 日志对象，方便记录异常
public class GlobalExceptionHandler {
    @ExceptionHandler(BusinessException.class)
    public BaseResponse<?>  businessExceptionHandler(BusinessException e) {
        log.error("BusinessException", e);
        return ResultUtils.error(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    public BaseResponse<?> runtimeExceptionHandler(RuntimeException e){
        log.error("RuntimeException",e);
        return ResultUtils.error(ErrorCode.SYSTEM_ERROR,"系统错误");
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)

    public BaseResponse<?> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e){
        log.error("MethodArgumentNotValidException",e);
        String message = e.getAllErrors().get(0).getDefaultMessage();
        return ResultUtils.error(ErrorCode.PARAMS_ERROR,message);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public BaseResponse<?> constraintViolationExceptionHandler(ConstraintViolationException e){
        String message = e.getConstraintViolations().stream().map(ConstraintViolation::getMessage).findFirst().orElse("请求参数校验失败");
        return ResultUtils.error(ErrorCode.PARAMS_ERROR,message);
    }
}
