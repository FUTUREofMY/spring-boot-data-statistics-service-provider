package com.bytefuture.data.config.property;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * 身份校验
 * @author kchen
 * @date 2023/05/23 16:55
 */
@Data
@Configuration
@ConfigurationProperties(prefix = AuthConfiguration.PREFIX)
public class AuthConfiguration {

    protected static final String PREFIX = "approval";

    private HashMap<String, Object> puSai = new HashMap<>();
    private HashMap<String, Object> oneWindow = new HashMap<>();
    private HashMap<String, Object> other = new HashMap<>();

    public Map<String, Object> matchNewObject(String id){
        switch (id) {
            case "puSai":
                return this.puSai;
            case "oneWindow":
                return this.oneWindow;
            case "2":
                return this.other;
            default:
                return null;
        }
    }



}
