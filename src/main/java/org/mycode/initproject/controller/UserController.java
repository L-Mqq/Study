package org.mycode.initproject.controller;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.mycode.initproject.model.dto.UserLoginPasswordRequest;
import org.mycode.initproject.model.dto.UserRegisterRequest;
import org.mycode.initproject.model.vo.LoginAndRegisterResponse;
import  org.mycode.initproject.service.UserService;
import org.mycode.initproject.service.impl.UserServiceImpl;
import jakarta.annotation.Resource;
import org.mycode.initproject.common.BaseResponse;
import org.mycode.initproject.common.ResultUtils;
import org.mycode.initproject.constant.UserConstant;
import org.mycode.initproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@Validated
public class UserController {

   @Autowired  //这个注解已经自动注入了
   private UserService userService;

//   发送验证码
    @GetMapping("/sendCaptcha")
    public BaseResponse<?> sendCaptcha(@RequestParam @NotBlank(message = "邮箱不能为空") @Email(message = "邮箱格式不正确") String targetEmail){
        userService.sendCaptcha(targetEmail);
        return ResultUtils.success(UserConstant.SEND_EMAIL_SUCCESS);
    }

//    注册
    @PostMapping("/register")
    public BaseResponse<LoginAndRegisterResponse> register(@RequestBody UserRegisterRequest userRegisterRequest){
        return ResultUtils.success (userService.register(userRegisterRequest));
    }

    @PostMapping("/login/password")
    public BaseResponse<LoginAndRegisterResponse> loginPassword(@RequestBody UserLoginPasswordRequest userLoginPasswordRequest){
        return ResultUtils.success(userService.loginPassword(userLoginPasswordRequest));
    }



}
