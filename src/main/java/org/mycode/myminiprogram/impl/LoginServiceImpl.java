package org.mycode.myminiprogram.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.micrometer.common.util.StringUtils;
import jakarta.annotation.Resource;
import org.mycode.myminiprogram.dto.UserDto;
import org.mycode.myminiprogram.service.LoginService;
import org.mycode.myminiprogram.vo.LoginVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jackson.autoconfigure.JacksonProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import tools.jackson.databind.json.JsonMapper;

import java.nio.file.LinkOption;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class LoginServiceImpl implements LoginService {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    // Token前缀，便于在Redis中识别和管理
    private static final String LOGIN_TOKEN_PREFIX = "login:token:";
    // Token有效期：30分钟
    private static final long TOKEN_EXPIRE_TIME = 30;

    @Resource
    private JsonMapper jsonMapper;

    @Override
    public LoginVo login(UserDto userDto) {
        Long userId = userDto.getUserId();
        String password = userDto.getPassword();

        //判断用户是否存在
        //判断密码是否正确

        //如果都正确的话就，生成token
        String token = UUID.randomUUID().toString().replace("-","");

        //构造一个不含密码的对象
        UserDto redisUser = new UserDto();
        redisUser.setUserId(userDto.getUserId());
        redisUser.setUsername(userDto.getUsername());

        //把token作为key 然后把用户信息存入redis  设置过期时间！！
        String tokenKey = LOGIN_TOKEN_PREFIX + token;
        String userJSON =jsonMapper.writeValueAsString(redisUser);
        stringRedisTemplate.opsForValue().set(tokenKey,userJSON,TOKEN_EXPIRE_TIME, TimeUnit.MINUTES);
        //返回用户信息
        LoginVo loginVo = new LoginVo();
        BeanUtils.copyProperties(redisUser,loginVo);
        loginVo.setToken(token);
        return loginVo;
    }


}
