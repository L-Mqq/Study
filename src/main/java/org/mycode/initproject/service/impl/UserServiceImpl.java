package org.mycode.initproject.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.micrometer.common.util.StringUtils;
import org.mycode.initproject.constant.UserConstant;
import org.mycode.initproject.model.User;
import org.mycode.initproject.mapper.UserMapper;
import jakarta.annotation.Resource;
import org.mycode.initproject.common.ErrorCode;
import org.mycode.initproject.exception.ThrowUtils;
import org.mycode.initproject.service.UserService;
import org.mycode.initproject.utils.EmailUtil;
import org.mycode.initproject.utils.RandomCodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
* @author WIND
* @description 针对表【user(用户表)】的数据库操作Service实现
* @createDate 2026-11-13 15:47:27
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService {

    @Autowired
    private EmailUtil emailUtil;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public void sendCaptcha(String targetEmail){
        //判断五分钟内有没有生成验证码 不为空报错
        String existCode = stringRedisTemplate.opsForValue().get(targetEmail);
        ThrowUtils.throwIf(StringUtils.isNotBlank(existCode),ErrorCode.LOGIN_SEND_CODE_ERROR);
        //生成验证码
        String randomNum = RandomCodeUtil.getRandomCode();
        //发送验证码
        emailUtil.sendEmail(targetEmail,randomNum);
        //把验证码按照邮箱存在redis里面
        stringRedisTemplate.opsForValue().set(targetEmail,randomNum, UserConstant.CAPTCHA_EXPIRE_TIME, TimeUnit.MINUTES);
    }

}




