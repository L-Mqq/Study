package org.mycode.initproject.common;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class BaseResponse<T> implements Serializable {  //java序列化 变成字节数据
    private int code;
    private T data;
    private  String message;

    public BaseResponse(int code,T data,String message){
        this.code = code;
        this.data = data;
        this.message = message;
    }

    //简化函数
    //this() 必须放在构造方法的第一条语句。
    //this是当前类的构造方法
    public BaseResponse(int code,T data){
        this(code,data,"");
    }

    public BaseResponse(ErrorCode errorCode){
        this(errorCode.getCode(),null,errorCode.getMessage());
    }
}
