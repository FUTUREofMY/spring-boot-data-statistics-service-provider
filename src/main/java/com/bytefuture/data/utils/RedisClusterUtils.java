package com.bytefuture.data.utils;

import redis.clients.jedis.JedisCluster;

import java.util.List;
import java.util.Set;

/**
 操作Redis集群方法封装类
 */
public class RedisClusterUtils {

    private static JedisCluster cluster;

    public static JedisCluster getCluster() { return cluster; }

    public static void initRedisCluster(JedisCluster clusterObj) {
        cluster = clusterObj;
    }

    /**
     * 通用回调函数
     */
    public static <T> T execute(RedisFunction<JedisCluster,T> function) {
        return function.call(cluster);
    }

    /**
     * 保存键值对
     */
    public static String set(String key, String value) {
        return set(key, value, null);
    }

    /**
     * 保存键值对，并设置时间
     */
    public static String set(String key, String value, Integer seconds) {
        return execute(new RedisFunction<JedisCluster, String>() {

            @Override
            public String call(JedisCluster cluster) {
                String result = cluster.set(key, value);
                if(seconds != null && seconds != -1 && seconds != 0) {
                    cluster.expire(key, seconds);
                }
                return result;
            }
        });
    }

    /**
     * 根据key获取
     */
    public static String get(final String key) {
        return execute(new RedisFunction<JedisCluster, String>() {

            @Override
            public String call(JedisCluster cluster) {
                return cluster.get(key);
            }
        });
    }

    /**
     * 根据keys删除
     */
    public static Long del(final String key) {
        return execute(new RedisFunction<JedisCluster, Long>() {

            @Override
            public Long call(JedisCluster cluster) {
                return cluster.del(key);
            }
        });
    }

    /**
     * 刷新时间
     */
    public static Long expire(final String key, Integer seconds) {
        return execute(new RedisFunction<JedisCluster, Long>() {

            @Override
            public Long call(JedisCluster cluster) {
                if(seconds != null && seconds != -1 && seconds != 0) {
                    cluster.expire(key, seconds);
                }
                return cluster.ttl(key);
            }
        });
    }

    /**
     * 是否存在
     */
    public static Boolean exists(final String key) {
        return execute(new RedisFunction<JedisCluster, Boolean>() {

            @Override
            public Boolean call(JedisCluster cluster) {
                return cluster.exists(key);
            }
        });
    }

    /**
     * 获取剩余时间，键不存在或已经过期，返回-1
     */
    public static Long ttl(final String key) {
        return execute(new RedisFunction<JedisCluster, Long>() {

            @Override
            public Long call(JedisCluster cluster) {
                if(exists(key)) {
                    return cluster.ttl(key);
                }
                return -1L;
            }
        });
    }

    /**
     * 存储到队列左侧
     */
    public static Long lpush(final String key, final String... values) {
        return execute(new RedisFunction<JedisCluster, Long>() {

            @Override
            public Long call(JedisCluster cluster) {
                return cluster.lpush(key, values);
            }
        });
    }

    /**
     * 从队列右侧获取
     */
    public static String rpop(final String key) {
        return execute(new RedisFunction<JedisCluster, String>() {

            @Override
            public String call(JedisCluster cluster) {
                return cluster.rpop(key);
            }
        });
    }

    /**
     * 按照一定的模式，匹配满足条件的key
     */
    public static Set<String> keys(final String pattern) {
        return execute(new RedisFunction<JedisCluster, Set<String>>() {

            @Override
            public Set<String> call(JedisCluster cluster) {
                return cluster.keys(pattern);
            }
        });
    }

    /**
     * 执行lua脚本
     */
    public static Object eval(String script, List<String> keys, List<String> args) {
        return execute(new RedisFunction<JedisCluster, Object>() {

            @Override
            public Object call(JedisCluster cluster) {
                return cluster.eval(script, keys, args);
            }
        });
    }
}
