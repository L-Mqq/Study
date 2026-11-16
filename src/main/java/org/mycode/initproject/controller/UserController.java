package org.mycode.initproject.controller;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import  org.mycode.initproject.service.UserService;
import org.mycode.initproject.service.impl.UserServiceImpl;
import jakarta.annotation.Resource;
import org.mycode.initproject.common.BaseResponse;
import org.mycode.initproject.common.ResultUtils;
import org.mycode.initproject.constant.UserConstant;
import org.mycode.initproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {

   @Autowired  //这个注解已经自动注入了
   private UserService userService;

    @GetMapping("/sendCaptcha")
    public BaseResponse<?> sendCaptcha(@RequestParam @NotBlank @Email String targetEmail){
        userService.sendCaptcha(targetEmail);
        return ResultUtils.success(UserConstant.SEND_EMAIL_SUCCESS);
    }
}
