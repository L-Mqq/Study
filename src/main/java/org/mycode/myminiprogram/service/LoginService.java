package org.mycode.myminiprogram.service;

import org.mycode.myminiprogram.dto.UserDto;
import org.mycode.myminiprogram.vo.LoginVo;

public interface LoginService  {
    LoginVo login(UserDto userDto);
}
