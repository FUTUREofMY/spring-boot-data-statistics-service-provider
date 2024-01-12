package com.bytefuture.data.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import redis.clients.jedis.JedisPoolConfig;

import java.util.Set;

/**
 * @author Administrator
 */
@ConfigurationProperties(prefix = RedisConfig.PREFIX)
public class RedisConfig {

    protected static final String PREFIX = "redis";

    /**
     * 连接配置，复用JedisPoolConfig
     */
    @NestedConfigurationProperty
    private JedisPoolConfig config = new JedisPoolConfig();

    /**
     * 节点：ip:port
     */
    private Set<String> nodes;

    /**
     * 集群密码：要求集群内所有节点密码一致
     */
    private String password;

    /**
     * 连接超时时间
     */
    private int connectionTimeout = 5000;

    /**
     * 读取数据超时时间
     */
    private int soTimeout = 5000;

    /**
     * redis连接池最大连接数,默认为8
     */
    private int maxTotalInJedisPool;

    /**
     * redis连接池最大空闲数,默认为8
     */
    private int maxIdleInJedisPool;

    /**
     * redis连接池最小空闲数,默认为0
     */
    private int minIdleInJedisPool;

    /**
     * 是否启用redis服务
     */
    private Boolean enable;

    /**
     * redis模式（单机：one，集群：cluster）
     */
    private String mode;

    /**
     * 最大尝试次数
     */
    private int maxAttempts = 3;

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    public JedisPoolConfig getConfig() {
        return config;
    }

    public void setConfig(JedisPoolConfig config) {
        this.config = config;
    }

    public Set<String> getNodes() {
        return nodes;
    }

    public void setNodes(Set<String> nodes) {
        this.nodes = nodes;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getConnectionTimeout() {
        return connectionTimeout;
    }

    public void setConnectionTimeout(int connectionTimeout) {
        this.connectionTimeout = connectionTimeout;
    }

    public int getSoTimeout() {
        return soTimeout;
    }

    public void setSoTimeout(int soTimeout) {
        this.soTimeout = soTimeout;
    }

    public int getMaxAttempts() {
        return maxAttempts;
    }

    public void setMaxAttempts(int maxAttempts) {
        this.maxAttempts = maxAttempts;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public int getMaxTotalInJedisPool() {
        return maxTotalInJedisPool;
    }

    public void setMaxTotalInJedisPool(int maxTotalInJedisPool) {
        this.maxTotalInJedisPool = maxTotalInJedisPool;
    }

    public int getMaxIdleInJedisPool() {
        return maxIdleInJedisPool;
    }

    public void setMaxIdleInJedisPool(int maxIdleInJedisPool) {
        this.maxIdleInJedisPool = maxIdleInJedisPool;
    }

    public int getMinIdleInJedisPool() {
        return minIdleInJedisPool;
    }

    public void setMinIdleInJedisPool(int minIdleInJedisPool) {
        this.minIdleInJedisPool = minIdleInJedisPool;
    }
}
