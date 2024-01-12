package com.bytefuture.data.common.service;

import com.bytefuture.data.config.RedisConfig;
import com.bytefuture.data.starter.JedisObject;
import com.bytefuture.data.utils.RedisUtils;
import com.bytefuture.data.utils.RedisClusterUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * redis 缓存service
 * @author kendrick Chen
 */
@Service
public class RedisService {

    @Autowired
    private RedisConfig redisConfig;

    /**
     * 获取redis缓存
     * @param key 键值
     * @return 对应key值的 数据
     */
    public String get(String key) {
        if (redisConfig.getMode().equals("one")) {
            return RedisUtils.get(key);
        } else {
            return RedisClusterUtils.get(key);
        }
    }

    /**
     * redis缓存设置（永久有效）
     * @param key 键值
     * @param value value值
     */
    public void set(String key, String value) {
        if (redisConfig.getMode().equals("one")) {
            RedisUtils.set(key, value);
        } else {
            RedisClusterUtils.set(key, value);
        }
    }

    /**
     * redis缓存设置（指定时间内有效）
     * @param key 键值
     * @param value value值
     * @param expiresTime 有效时间
     */
    public void set(String key, String value, Integer expiresTime) {
        if (redisConfig.getMode().equals("one")) {
            RedisUtils.setex(key, value, expiresTime.longValue());
        } else {
            RedisClusterUtils.set(key, value, expiresTime);
        }
    }

    /**
     * redis初始化
     * @param redisConfig redis配置文件
     * @return redis初始化成功或失败
     * @throws Exception e
     */
    public static JedisObject initRedis(RedisConfig redisConfig) throws Exception {
        if (redisConfig.getMode().equals("one")) {
            JedisPool jedisPool = initJedisPool(redisConfig);
            JedisObject jedisObject = new JedisObject();
            jedisObject.setJedisPool(jedisPool);
            return jedisObject;
        } else if (redisConfig.getMode().equals("cluster")) {
            JedisCluster jedisCluster = initRedisCluster(redisConfig);
            JedisObject jedisObject = new JedisObject();
            jedisObject.setJedisCluster(jedisCluster);
            return jedisObject;
        } else {
            throw new Exception("未知的redis启动模式！");
        }
    }

    /**
     * 单机模式redis初始化
     * @param redisConfig redis配置文件
     * @return 单机模式redis初始化成功或失败
     * @throws Exception e
     */
    private static JedisPool initJedisPool(RedisConfig redisConfig) throws Exception {
        if (null == redisConfig || !redisConfig.getEnable()) {
            return null;
        } else {

            if (redisConfig.getNodes().size() == 0) {
                throw new Exception("redis服务器地址未填写！配置参数名：redis.nodes，多个地址请以逗号隔开");
            }

            Set<String> nodes = redisConfig.getNodes();
            List<String> nodeList = new ArrayList<>(nodes);

            String[] hp = null;
            if (!CollectionUtils.isEmpty(nodeList)) {
                hp = nodeList.get(0).split(":");
            } else {
                throw new Exception("redis服务器节点未配置，请先配置！");
            }

            if(StringUtils.isEmpty(redisConfig.getPassword())) {
                redisConfig.setPassword(null);
            }

            // 设置redis连接池参数
            JedisPoolConfig jedisPoolConfig = redisConfig.getConfig();
            if (redisConfig.getMaxTotalInJedisPool() > 0) {
                jedisPoolConfig.setMaxTotal(redisConfig.getMaxTotalInJedisPool());
            }
            if (redisConfig.getMaxIdleInJedisPool() > 0) {
                jedisPoolConfig.setMaxIdle(redisConfig.getMaxIdleInJedisPool());
            }
            if (redisConfig.getMinIdleInJedisPool() > 0) {
                jedisPoolConfig.setMinIdle(redisConfig.getMinIdleInJedisPool());
            }

            try {
                JedisPool pool = new JedisPool(jedisPoolConfig, hp[0], Integer.valueOf(hp[1]).intValue(),
                        redisConfig.getConnectionTimeout(), redisConfig.getPassword());
                RedisUtils.initJedisPool(pool);
                return pool;
            } catch (Exception e) {
                throw e;
            }
        }
    }

    /**
     * 集群模式redis初始化
     * @param redisConfig redis配置文件
     * @return 集群模式redis初始化成功或失败
     * @throws Exception e
     */
    private static JedisCluster initRedisCluster(RedisConfig redisConfig) throws Exception {
        if (null == redisConfig || !redisConfig.getEnable()) {
            return null;
        } else {

            if (redisConfig.getNodes().size() == 0) {
                throw new Exception("redis集群服务器地址未填写！配置参数名：redis.nodes，多个地址请以逗号隔开");
            }
            Set<String> nodes = redisConfig.getNodes();
            Set<HostAndPort> hostAndPorts = new HashSet<HostAndPort>();
            String[] hp = null;
            for (String node : nodes) {
                hp = node.split(":");
                hostAndPorts.add(new HostAndPort(hp[0], Integer.parseInt(hp[1])));
            }
            if(StringUtils.isEmpty(redisConfig.getPassword())) {
                redisConfig.setPassword(null);
            }
            try {
                JedisCluster cluster = new JedisCluster(hostAndPorts, redisConfig.getConnectionTimeout(), redisConfig.getSoTimeout(), redisConfig.getMaxAttempts(), redisConfig.getPassword(), redisConfig.getConfig());
                RedisClusterUtils.initRedisCluster(cluster);
                return cluster;
            } catch (Exception e) {
                throw e;
            }
        }
    }
}
