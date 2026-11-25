package org.mycode.myminiprogram.controller;

import org.mycode.myminiprogram.dto.LoginDto;
import org.mycode.myminiprogram.dto.UserDto;
import org.mycode.myminiprogram.entity.User;
import org.mycode.myminiprogram.service.LoginService;
import org.mycode.myminiprogram.vo.LoginVo;
import org.mycode.myminiprogram.vo.Result;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class LoginController {

    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping("/login")
   public LoginVo login(@RequestBody LoginDto loginDto ){
        return loginService.login(loginDto);
    }

    @GetMapping("/info")
    public UserDto getInfo(){
        return loginService.getInfo();
    }


}
