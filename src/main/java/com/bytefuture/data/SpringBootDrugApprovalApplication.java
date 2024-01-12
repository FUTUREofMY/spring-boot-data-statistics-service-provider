package com.bytefuture.data;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;

import java.net.UnknownHostException;

@Slf4j
@MapperScan("com.bytefuture.data.**.mapper")
@ComponentScan(basePackages = {"com.bytefuture.data.*"})
@SpringBootApplication
class SpringBootDrugApprovalApplication {

    public static void main(String[] args) throws UnknownHostException {
        ConfigurableApplicationContext application = SpringApplication.run(SpringBootDrugApprovalApplication.class, args);
        Environment env = application.getEnvironment();
        log.info("\n----------------------------------------------------------\n\t" +
                        "应用 '{}' 运行成功! 访问连接:\n\t" +
                        "数据库监控: \t\thttp://{}:{}{}/druid\n" +
                        "----------------------------------------------------------",
                env.getProperty("spring.application.name"),
                env.getProperty("spring.datasource.druid.statViewServlet.visitUrl", "127.0.0.1"),
                env.getProperty("server.port", "26091"),
                env.getProperty("server.servlet.context-path", "")
        );
    }
}
