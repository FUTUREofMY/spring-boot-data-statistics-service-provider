package com.bytefuture.data.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/** RestTemplate配置 */
@Configuration
public class RestTemplateConfig {

  @Bean
  public RestTemplate restTemplate(ClientHttpRequestFactory factory) {
    return new RestTemplate(factory);
  }

  @Bean
  public ClientHttpRequestFactory simpleClientHttpRequestFactory() {
    SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
    // 超时设置 ms
    factory.setReadTimeout(300000);
    factory.setConnectTimeout(300000);
    return factory;
  }
}
