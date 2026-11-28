package org.mycode.redisseckilldemo.consumer;

import com.alibaba.fastjson2.JSON;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.mycode.redisseckilldemo.model.SeckillOrder;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.concurrent.TimeUnit;

@Slf4j
public class OrderConsumer {

    private static final String ORDER_QUEUE = "seckill:orders";

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    //应用启动：Spring 调用 start() → 创建并启动 worker
    //运行期间：worker 执行 consumeLoop()，不断取订单
    //应用关闭：Spring 调用 stop() → 把 running 改为 false
    //                               → worker 看到后退出
    private volatile boolean running = true;
    private Thread worker;

    @PostConstruct
    public void start(){
        worker = new Thread(this::comsumeLoop,"order-consumer");
        worker.setDaemon(running);
        worker.start();
    }

    public void comsumeLoop(){
        log.info("取出订单...");
        try {
            while (running){
                String orderJson =stringRedisTemplate.opsForList().rightPop(ORDER_QUEUE,3, TimeUnit.SECONDS);
                if (orderJson != null){
                    SeckillOrder seckillOrder = JSON.parseObject(orderJson,SeckillOrder.class);
                    proccessOrder(seckillOrder);
                }
            }
        }catch (Exception e){
            log.error("订单异常",e);
            sleepQuietly(100);
        }

        log.info("订单消费退出...");
    }








    public void proccessOrder(SeckillOrder seckillOrder){
        log.info("开始处理订单..."+ seckillOrder.getUserId());

        sleepQuietly(100);

        log.info("订单处理完成" + seckillOrder.getUserId());

    }






    private void sleepQuietly(long millis){
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

    }



    public void stop(){
        running = false;
        if (worker != null){
            worker.interrupt();
        }
    }
}
