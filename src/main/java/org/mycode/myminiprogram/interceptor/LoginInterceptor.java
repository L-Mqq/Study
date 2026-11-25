package org.mycode.myminiprogram.interceptor;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.mycode.myminiprogram.dto.UserDto;
import org.mycode.myminiprogram.utils.UserHolder;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import tools.jackson.databind.json.JsonMapper;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    // Token前缀，便于在Redis中识别和管理
    private static final String LOGIN_TOKEN_PREFIX = "login:token:";
    // Token有效期：30分钟
    private static final long TOKEN_EXPIRE_TIME = 30;

    @Resource
    private UserHolder userHolder;

    @Resource
    private JsonMapper jsonMapper;

    public boolean preHandle  (HttpServletRequest request, HttpServletResponse response,Object handler){
        //获取用户的token
        String token = request.getHeader("token");
        if (token == null || token.isBlank()){
           throw new IllegalArgumentException("token无效");
        }

        //在redis里面查找对应的用户信息
        String key = LOGIN_TOKEN_PREFIX + token;
        String userDtoJson = stringRedisTemplate.opsForValue().get(key);
        if (userDtoJson == null){
            throw new IllegalArgumentException("token无效");
        }


        //如果不存在 拒绝请求 如果存在
        //把用户信息放在userholder 后面就可以直接使用用户信息  token续期！！！
        UserDto userDto = jsonMapper.readValue(userDtoJson,UserDto.class);
        System.out.println("Redis获取的用户：" + userDto);
        userHolder.setUser(userDto);

        //token续期！！！
        //stringRedisTemplate.expire(key,TOKEN_EXPIRE_TIME, TimeUnit.MINUTES);
        boolean renewed = stringRedisTemplate.expire(key, Duration.ofMinutes(TOKEN_EXPIRE_TIME));


        return true;
}
        @Override
        public void afterCompletion(HttpServletRequest request,HttpServletResponse response,Object handler,Exception ex) throws Exception{
        //放行请求  清理请求！！ 应该在请求完成后才清除用户信息
        userHolder.delUser();

    }

}
