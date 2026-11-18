package org.mycode.initproject.handler;


import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.mycode.initproject.common.ErrorCode;
import org.mycode.initproject.constant.UserConstant;
import org.mycode.initproject.exception.BusinessException;
import org.mycode.initproject.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
@Slf4j
public class JwtHandler implements HandlerInterceptor {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,Object handler){
        //取出token
        String accessToken = request.getHeader("Access-Token");
        String refreshToken = request.getHeader("Refresh-Token");
        try {
            //判断第一个是否有效
            if (accessToken != null){
                //1.解析jwt
                Claims acParse = JwtUtil.parse(accessToken);
                if (acParse != null){
                    //2.获取id
                    String userId = acParse.getSubject();
                    //3.从redis中获取token
                    String redisAccessToken = stringRedisTemplate.opsForValue().get(UserConstant.ACCESS_TOKEN_PREFIX + userId);
                    //4.对token进行比对
                    if (accessToken.equals(redisAccessToken)){
                        request.setAttribute("userId",userId);
                        return true;
                    }
                }
            }

            //如果没有效判断第二个 有效的话无感刷新accesstoken
            if (refreshToken!=null){
                Claims rfParse = JwtUtil.parse(refreshToken);
                if (rfParse != null){
                    String userId = rfParse.getSubject();
                    String redisRefreshToken = stringRedisTemplate.opsForValue().get(UserConstant.REFRESH_TOKEN_PREFIX + userId);
                    if (refreshToken.equals(redisRefreshToken)){
                        throw new BusinessException(ErrorCode.TOKEN_EXPIRED);
                    }
                }
            }
        }catch (BusinessException e){
            throw e;  //业务异常 也就是对应上面那个accessToken过期
        }catch (Exception e){
            //系统的异常
            log.error("JWT 校验系统异常", e);
            throw new BusinessException(ErrorCode.SYSTEM_ERROR,"登录状态确认失败");

        }
        throw  new BusinessException(ErrorCode.NOT_LOGIN_ERROR);
    }


}
