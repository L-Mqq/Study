package org.mycode.myminiprogram.controller;

import org.mycode.myminiprogram.dto.UserDto;
import org.mycode.myminiprogram.service.LoginService;
import org.mycode.myminiprogram.vo.LoginVo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class LoginController {

    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping("/login")
    public LoginVo login(@RequestBody UserDto userDto){
        return loginService.login(userDto);
    }
}
