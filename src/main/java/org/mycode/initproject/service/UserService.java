package org.mycode.initproject.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.mycode.initproject.common.BaseResponse;
import org.mycode.initproject.model.dto.UserLoginCodeRequest;
import org.mycode.initproject.model.dto.UserLoginPasswordRequest;
import org.mycode.initproject.model.dto.UserRegisterRequest;
import org.mycode.initproject.model.entity.User;
import org.mycode.initproject.model.vo.LoginAndRegisterResponse;

/**
* @author WIND
* @description 针对表【user(用户表)】的数据库操作Service
* @createDate 2026-11-13 15:47:27
*/
public interface UserService extends IService<User> {

    void sendCaptcha(String targetEmail);

    LoginAndRegisterResponse register(UserRegisterRequest userRegisterRequest);

    LoginAndRegisterResponse loginPassword(UserLoginPasswordRequest userLoginPasswordRequest);

    LoginAndRegisterResponse loginCode(UserLoginCodeRequest userLoginCodeRequest);
}
