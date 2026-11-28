package org.mycode.redisseckilldemo.controller;

import com.alibaba.fastjson2.JSON;
import jakarta.annotation.Resource;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.mycode.redisseckilldemo.model.SeckillOrder;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

import static org.mycode.redisseckilldemo.common.RedisKeyConstants.ORDER_QUEUE;

@Slf4j
@RestController
@RequestMapping("/seckill")
public class seckillController {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    private static final String STOCK_KEY = "seckill:product:1001:stock";

    //管理员接口 初始化库存
    @PostMapping("/init")
    public String initStock(@RequestParam Integer stock){
        stringRedisTemplate.opsForValue().set(STOCK_KEY, String.valueOf(stock));
        return "库存初始化为"+stock;
    }
    
    //获取库存
    @GetMapping("/stock")
    public String getStock(){
        String stock =  stringRedisTemplate.opsForValue().get(STOCK_KEY);
        return stock;
    }

    //用户秒杀
    @PostMapping("/kill")
    public Map<String,Object> seckill(@RequestParam Long userId,@RequestParam Long productId){
        //创建hashmap 用于存放结果   `success`、`message`、`userId`
        Map<String,Object> result = new HashMap<>();

        //扣减库存 再去判断库存够不够
        Long stock = stringRedisTemplate.opsForValue().decrement(STOCK_KEY);

        //检查库存 null？<0?
        if (stock == null){
            result.put("success","false");
            result.put("message","抢购失败");
            result.put("userId",userId);
            throw new IllegalArgumentException("无法获取库存");

        }
        if (stock < 0){
            stringRedisTemplate.opsForValue().increment(STOCK_KEY);
            result.put("success","false");
            result.put("message","抢购失败");
            result.put("userId",userId);
        }

        //成功则入队
        if (stock >= 0){
            //把订单入队
            SeckillOrder seckillOrder = new SeckillOrder(userId,productId,System.currentTimeMillis());
            stringRedisTemplate.opsForList().leftPush(ORDER_QUEUE, JSON.toJSONString(seckillOrder));

            result.put("success","true");
            result.put("message","抢购成功，等待订单处理");
            result.put("userId",userId);
        }

        //返回结果
        return result;
    }
}
