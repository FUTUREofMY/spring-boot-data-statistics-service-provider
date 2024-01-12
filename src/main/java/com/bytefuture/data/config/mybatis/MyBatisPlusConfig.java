package com.bytefuture.data.config.mybatis;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.BlockAttackInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @Description
 *
 * @author min.yang
 * @date 2022/4/13 10:22
 * @return
 */
@Configuration
@EnableTransactionManagement
public class MyBatisPlusConfig {

    /**
     *  注册mybatis-plus插件
     */
    @Bean
    public MybatisPlusInterceptor paginationInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new BlockAttackInnerInterceptor()); // 防止全表更新与删除
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL)); // 自动分页
        return interceptor;
    }
}
