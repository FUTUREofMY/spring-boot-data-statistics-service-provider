package com.bytefuture.data.config;

import com.bytefuture.data.interceptor.AuthInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

/**
 * @author KendrickChen
 * @date 2023/5/24  10:40
 */
@Configuration
public class AuthConfigurer implements WebMvcConfigurer {
    @Resource private AuthInterceptor authInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authInterceptor).addPathPatterns("/yjexchange-svr/notice/*", "/license-xgs-drugs/*");
    }
}
