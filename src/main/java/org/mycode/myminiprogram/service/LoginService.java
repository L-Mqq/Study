package org.mycode.myminiprogram.service;

import org.mycode.myminiprogram.dto.LoginDto;
import org.mycode.myminiprogram.dto.UserDto;
import org.mycode.myminiprogram.vo.LoginVo;
import org.mycode.myminiprogram.vo.Result;

public interface LoginService  {
    LoginVo login(LoginDto loginDto);
    UserDto getInfo();
}
