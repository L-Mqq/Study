package org.mycode.redisseckilldemo.common;

/**
 * Redis Key 统一管理
 *
 * <p><b>设计约定</b>：所有"商品维度"的 Key 一律通过方法拼接 {@code productId} 生成，
 * 类中<b>不保留任何写死 productId 的 public 常量</b>。</p>
 *
 * <p>为什么要这样约定？原实现同时存在 {@code STOCK_KEY = "seckill:product:1001:stock"}
 * 这样的写死常量和一个 {@code productId} 接口参数，调用方很容易一边收着参数、
 * 一边引用着常量，最终"扣 A 商品的库存、生成 B 商品的订单"，而且不报任何错。
 * 把常量删掉、只留下必须传参的方法，编译器就会强制调用方交出 productId，
 * 这类不一致从此无法写出来——<b>靠类型约束而不是靠注释提醒</b>。</p>
 */
public final class RedisKeyConstants {

    /** 商品维度 Key 的公共前缀 */
    private static final String PRODUCT_PREFIX = "seckill:product:";

    /**
     * 订单消息队列（List：LPUSH 左进 + BRPOP 右出 = 先进先出）
     *
     * <p>这是唯一保留为常量的 Key：订单队列是<b>全局单队列</b>，不按商品拆分。
     * 消费者需要 productId 时从订单消息体里读（{@code SeckillOrder#getProductId()}），
     * 因此这里不需要、也不应该拼接 productId。</p>
     */
    public static final String ORDER_QUEUE = "seckill:orders";

    /** 库存 Key（String，由 Lua 脚本原子扣减） */
    public static String stockKey(long productId) {
        return PRODUCT_PREFIX + productId + ":stock";
    }

    /** 已购用户集合 Key（Set，"一人一单"依据，活动期内长期有效，不设短过期） */
    public static String boughtSet(long productId) {
        return PRODUCT_PREFIX + productId + ":users";
    }

    /** 防抖限流 Key（String + SET NX EX，"防连点"，秒级自动过期） */
    public static String rateKey(long productId, long userId) {
        return PRODUCT_PREFIX + productId + ":rate:" + userId;
    }

    private RedisKeyConstants() {
        // 常量类禁止实例化
    }
}