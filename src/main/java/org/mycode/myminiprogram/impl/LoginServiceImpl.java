package org.mycode.myminiprogram.impl;

import jakarta.annotation.Resource;
import org.mycode.myminiprogram.dto.LoginDto;
import org.mycode.myminiprogram.vo.Result;
import org.mycode.myminiprogram.dto.UserDto;
import org.mycode.myminiprogram.service.LoginService;
import org.mycode.myminiprogram.utils.UserHolder;
import org.mycode.myminiprogram.vo.LoginVo;
import org.springframework.beans.BeanUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import tools.jackson.databind.json.JsonMapper;

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

    @Resource
    private UserHolder userHolder;

    @Override
    public LoginVo login(LoginDto loginDto) {
        String username = loginDto.getUsername();
        String password = loginDto.getPassword();

        //判断用户是否存在
        //判断密码是否正确  目前没有使用数据库 只使用了测试数据
        UserDto user = validateUser(username, password);

        //如果都正确的话就，生成token
        String token = UUID.randomUUID().toString().replace("-","");

        //构造一个不含密码的对象
        UserDto userDto = new UserDto();
        userDto.setUsername(username);
        userDto.setNickname("测试");

        //把token作为key 然后把用户信息存入redis  设置过期时间！！
        String tokenKey = LOGIN_TOKEN_PREFIX + token;
        String userJSON =jsonMapper.writeValueAsString(userDto);
        stringRedisTemplate.opsForValue().set(tokenKey,userJSON,TOKEN_EXPIRE_TIME, TimeUnit.MINUTES);

        //返回用户信息
        LoginVo loginVo = new LoginVo();
        BeanUtils.copyProperties(userDto,loginVo);
        loginVo.setToken(token);
        return loginVo;
    }

    @Override
    public UserDto getInfo() {
       return userHolder.getUser();
    }

    /**
     * 验证用户凭据
     * 实际项目中应查询数据库并验证密码
     * 这是一个简化的演示实现
     */
    private UserDto validateUser(String username, String password) {
        // 演示：接受admin/123456或test/123456作为有效凭据
        if ("admin".equals(username) && "123456".equals(password)) {
            return new UserDto(1L, "admin", "管理员");
        }
        if ("test".equals(username) && "123456".equals(password)) {
            return new UserDto(2L, "test", "测试用户");
        }
        return null;
    }


}
