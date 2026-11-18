package org.mycode.initproject.service.impl;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.hutool.core.bean.BeanUtil;
import io.micrometer.common.util.StringUtils;
import org.apache.commons.codec.cli.Digest;
import org.mycode.initproject.constant.UserConstant;
import org.mycode.initproject.model.dto.UserLoginCodeRequest;
import org.mycode.initproject.model.dto.UserLoginPasswordRequest;
import org.mycode.initproject.model.dto.UserRegisterRequest;
import org.mycode.initproject.model.entity.User;
import org.mycode.initproject.mapper.UserMapper;
import org.mycode.initproject.common.ErrorCode;
import org.mycode.initproject.exception.ThrowUtils;
import org.mycode.initproject.model.vo.LoginAndRegisterResponse;
import org.mycode.initproject.service.UserService;
import org.mycode.initproject.utils.EmailUtil;
import org.mycode.initproject.utils.JwtUtil;
import org.mycode.initproject.utils.RandomCodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.commons.util.IdUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Objects;
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

    @Override
    public LoginAndRegisterResponse register(UserRegisterRequest userRegisterRequest) {
        String email = userRegisterRequest.getEmail();
        String password = userRegisterRequest.getPassword();
        String confirmPassword  = userRegisterRequest.getConfirmPassword();
        String code = userRegisterRequest.getCode();

        //邮箱是否存在
        ThrowUtils.throwIf(getUser(email)!=null,ErrorCode.USER_ALREADY_EXISTS);

        //密码是否一致
        ThrowUtils.throwIf((!password.equals(confirmPassword)),ErrorCode.LOGIN_PASSWORD_ERROR);

        //验证码是否正确、有效
        String redisCode = stringRedisTemplate.opsForValue().get(email);
        ThrowUtils.throwIf(StringUtils.isBlank(redisCode) || !code.equals(redisCode),ErrorCode.LOGIN_ERROR_CODE);

        //密码加密
        String encryptedPassword = DigestUtils.md5DigestAsHex((UserConstant.PASSWORD_SALT + password).getBytes());

        //写入数据库
        LoginAndRegisterResponse loginAndRegisterResponse = new LoginAndRegisterResponse();
        synchronized (email.intern()){
            Snowflake snowflake = IdUtil.getSnowflake(UserConstant.WORKER_ID,UserConstant.DATA_CENTER_ID);

            User newUser = new User();
            newUser.setUserId(snowflake.nextId());
            newUser.setPassword(encryptedPassword);
            newUser.setNickname(userRegisterRequest.getNickname());
            newUser.setEmail(email);

            //写入数据库
            boolean saveUser = save(newUser);
            ThrowUtils.throwIf(!saveUser,ErrorCode.REGISTER_ERROR);
            //这里的user要从数据库拿 不能直接使用上面newUser 信息不全
            BeanUtil.copyProperties(getUser(email),loginAndRegisterResponse);
        }
        stringRedisTemplate.delete(email);
        return createJWT(loginAndRegisterResponse);

    }

    @Override
    public LoginAndRegisterResponse loginPassword(UserLoginPasswordRequest userLoginPasswordRequest) {
        String email = userLoginPasswordRequest.getEmail();
        String password = userLoginPasswordRequest.getPassword();

        //判断用户是否存在
        User user = getUser(email);
        ThrowUtils.throwIf(user == null ,ErrorCode.USER_NOT_EXISTS);

        //判断密码是否正确
        String encryptedCode = DigestUtils.md5DigestAsHex(((UserConstant.PASSWORD_SALT) + password).getBytes());
        ThrowUtils.throwIf(!encryptedCode.equals(user.getPassword()),ErrorCode.LOGIN_ERROR);

        LoginAndRegisterResponse loginAndRegisterResponse = new LoginAndRegisterResponse();
        BeanUtil.copyProperties(user,loginAndRegisterResponse);
        return createJWT(loginAndRegisterResponse);

    }

    @Override
    public LoginAndRegisterResponse loginCode(UserLoginCodeRequest userLoginCodeRequest) {
        String email = userLoginCodeRequest.getEmail();
        String code = userLoginCodeRequest.getCode();

        //判断用户是否存在
        User user = getUser(email);
        ThrowUtils.throwIf(user == null,ErrorCode.USER_NOT_EXISTS);

        //验证码是否正确
        String existCode = stringRedisTemplate.opsForValue().get(email);
        ThrowUtils.throwIf(StringUtils.isBlank(existCode)||!code.equals(existCode),ErrorCode.LOGIN_ERROR_CODE);

        stringRedisTemplate.delete(email);

        LoginAndRegisterResponse loginAndRegisterResponse = new LoginAndRegisterResponse();
        BeanUtil.copyProperties(user,loginAndRegisterResponse);
        return createJWT(loginAndRegisterResponse);


    }

    public User getUser(String email){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("email",email);
        return this.getOne(queryWrapper);
    }

    public LoginAndRegisterResponse createJWT(LoginAndRegisterResponse loginAndRegisterResponse){
       String userId = loginAndRegisterResponse.getUserId().toString();
       String accessToken = JwtUtil.generate(userId,UserConstant.ACCESS_TOKEN_EXPIRE_TIME,UserConstant.ACCESS_TOKEN_UNIT);
       String refreshToken = JwtUtil.generate(userId,UserConstant.REFRESH_TOKEN_EXPIRE_TIME,UserConstant.REFRESH_TOKEN_UNIT);

       loginAndRegisterResponse.setAccessToken(accessToken);
       loginAndRegisterResponse.setRefreshToken(refreshToken);

        //把token存在redis里面！！
        stringRedisTemplate.opsForValue().set(UserConstant.ACCESS_TOKEN_PREFIX + userId,accessToken,UserConstant.ACCESS_TOKEN_EXPIRE_TIME,UserConstant.ACCESS_TOKEN_UNIT);
        stringRedisTemplate.opsForValue().set(UserConstant.REFRESH_TOKEN_PREFIX + userId,refreshToken,UserConstant.REFRESH_TOKEN_EXPIRE_TIME,UserConstant.REFRESH_TOKEN_UNIT);

       return loginAndRegisterResponse;
    }

    public boolean logout(String userId){
        stringRedisTemplate.delete(UserConstant.ACCESS_TOKEN_PREFIX + userId);
        stringRedisTemplate.delete(UserConstant.REFRESH_TOKEN_PREFIX + userId);
        return true;
    }


}




