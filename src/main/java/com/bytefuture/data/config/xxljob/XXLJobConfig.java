package com.bytefuture.data.config.xxljob;

import com.xxl.job.core.executor.impl.XxlJobSpringExecutor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.commons.util.InetUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * xxl-job配置类
 * @author zengchongchong
 * @date 2023/11/8 17:43
 **/
@Slf4j
@Configuration
@PropertySource("classpath:/xxljob.properties")
public class XXLJobConfig {

    @Value("${xxl.job.admin.addresses}")
    private String adminAddresses;

    @Value("${xxl.job.accessToken}")
    private String accessToken;

    @Value("${xxl.job.executor.appname}")
    private String appname;

    @Value("${xxl.job.executor.address}")
    private String address;

    @Value("${xxl.job.executor.ip}")
    private String ip;

    @Value("${xxl.job.executor.port}")
    private int port;

    @Value("${xxl.job.executor.logpath}")
    private String logPath;

    @Value("${xxl.job.executor.logretentiondays}")
    private int logRetentionDays;

    @Autowired
    private InetUtils inetUtils;

    @Bean
    public XxlJobSpringExecutor xxlJobExecutor() {
        log.info(">>>>>>>>>>> xxl-job config init.");
        log.info("xxl.job.admin.addresses" + adminAddresses);
        XxlJobSpringExecutor xxlJobSpringExecutor = new XxlJobSpringExecutor();
        xxlJobSpringExecutor.setAdminAddresses(adminAddresses);
        xxlJobSpringExecutor.setAppname(appname);
        xxlJobSpringExecutor.setAddress(address);
        xxlJobSpringExecutor.setPort(port);
        xxlJobSpringExecutor.setAccessToken(accessToken);
        xxlJobSpringExecutor.setLogPath(logPath);
        xxlJobSpringExecutor.setLogRetentionDays(logRetentionDays);

        String firstIpAddress = inetUtils.findFirstNonLoopbackHostInfo().getIpAddress();
        log.info("xxl.job.executor.ip->findFirstNonLoopbackHostInfo:" + firstIpAddress);
        xxlJobSpringExecutor.setIp(firstIpAddress);

        return xxlJobSpringExecutor;
    }

    /**
     * 普通定时任务
     *
     * @XxlJob("jobHandler")
     * public void jobHandler() throws Exception {
     *      // 业务逻辑...
     * }
     *
     */

    /**
     * 分片广播任务
     *
     * @XxlJob("shardingJobHandler")
     * public void shardingJobHandler() throws Exception {
     *      // 分片参数
     *      int shardIndex = XxlJobHelper.getShardIndex();
     *      int shardTotal = XxlJobHelper.getShardTotal();
     *      XxlJobHelper.log("分片参数：当前分片序号 = {}, 总分片数 = {}", shardIndex, shardTotal);
     * }
     *
     */

    /**
     * 针对多网卡、容器内部署等情况，可借助 "spring-cloud-commons" 提供的 "InetUtils" 组件灵活定制注册IP；
     *
     *      1、引入依赖：
     *          <dependency>
     *             <groupId>org.springframework.cloud</groupId>
     *             <artifactId>spring-cloud-commons</artifactId>
     *             <version>${version}</version>
     *         </dependency>
     *
     *      2、配置文件，或者容器启动变量
     *          spring.cloud.inetutils.preferred-networks: 'xxx.xxx.xxx.'
     *          或
     *          spring.cloud.inetutils.preferred-networks: 192.168.1\.(.*)
     *
     *      3、获取IP
     *          String ip_ = inetUtils.findFirstNonLoopbackHostInfo().getIpAddress();
     */
}
