package com.bytefuture.data.starter;

import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;

/**
 * @author Administrator
 */
public class JedisObject {

    private JedisPool jedisPool;
    private JedisCluster jedisCluster;

    public JedisPool getJedisPool() { return jedisPool; }

    public void setJedisPool(JedisPool jedisPool) {
        this.jedisPool = jedisPool;
    }

    public JedisCluster getJedisCluster() { return jedisCluster; }

    public void setJedisCluster(JedisCluster jedisCluster) {
        this.jedisCluster = jedisCluster;
    }
}
