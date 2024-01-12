package com.bytefuture.data.utils;

import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.List;

// redis单台（非集群）操作类
@Slf4j
public class RedisUtils {

    private static JedisPool jedisPool;

    public static JedisPool getJedisPool() { return jedisPool; }

    public static void initJedisPool(JedisPool jedisPoolObj) {
        jedisPool = jedisPoolObj;
    }

    private static Jedis getRedisObj() {

        Jedis jedis = null;
        try{
            jedis = jedisPool.getResource();
        }catch (Exception e){
            log.error("redis连接池异常" + e.getMessage());
        }

        return jedis;
    }

    /**
     * 获取数据
     * @key 数据键值
     * @return 返回数据
     */
    public static String get(String key){

        String value = "";

        Jedis jedis = getRedisObj();
        if (null != jedis) {
            value = jedis.get(key);
            jedis.close();
        }

        return value;
    }

    /**
     * 更新数据
     * @key 数据键值
     * @value 数据值
     */
    public static void set(String key, String value){

        Jedis jedis = getRedisObj();
        if (null != jedis) {
            jedis.set(key, value);
            jedis.close();
        }
    }

    /**
     * 更新数据
     * @key 数据键值
     * @value 数据值
     */
    public static void setex(String key, String value, long expireSecond){
        Jedis jedis = getRedisObj();
        if (null != jedis) {
            jedis.setex(key, expireSecond, value);
            jedis.close();
        }
    }

    /**
     * 获取数据
     * @key 数据键值
     * @return 返回数据
     */
    public static List<String> hmget(String key, String[] keyList){

        Jedis jedis = getRedisObj();

        List<String> valueList = null;

        if (null != jedis) {
            if (keyList.length == 1) {
                valueList = jedis.hmget(key, keyList[0]);
            } else if (keyList.length == 2) {
                valueList = jedis.hmget(key, keyList[0], keyList[1]);
            } else if (keyList.length == 3) {
                valueList = jedis.hmget(key, keyList[0], keyList[1], keyList[2]);
            } else if (keyList.length == 4) {
                valueList = jedis.hmget(key, keyList[0], keyList[1], keyList[2], keyList[3]);
            } else if (keyList.length == 5) {
                valueList = jedis.hmget(key, keyList[0], keyList[1], keyList[2], keyList[3], keyList[4]);
            }

            jedis.close();
        }

        return valueList;
    }
}
