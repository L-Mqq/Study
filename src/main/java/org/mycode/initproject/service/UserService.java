package org.mycode.initproject.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.mycode.initproject.model.User;
import org.springframework.stereotype.Component;

/**
* @author WIND
* @description 针对表【user(用户表)】的数据库操作Service
* @createDate 2026-11-13 15:47:27
*/
public interface UserService extends IService<User> {

    public void sendCaptcha(String targetEmail);

}
