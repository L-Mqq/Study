package org.mycode.myminiprogram.utils;

import org.mycode.myminiprogram.dto.UserDto;
import org.springframework.stereotype.Component;

//保存各个线程用户的信息
@Component
public class UserHolder {
    private static final  ThreadLocal<UserDto> tl = new ThreadLocal<>();

    public static void setUser(UserDto userDto){
        tl.set(userDto);
    }

    public static UserDto getUser(){
        return tl.get();
    }

    //清除线程 防止造成信息泄露
    public static void delUser(){
        tl.remove();
    }


}
