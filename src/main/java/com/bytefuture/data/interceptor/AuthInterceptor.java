package com.bytefuture.data.interceptor;

import com.bytefuture.data.config.property.AuthConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author KendrickChen
 * @date 2023/5/23  18:28
 */
@Component
@Slf4j
public class AuthInterceptor implements HandlerInterceptor {

    @Autowired
    private AuthConfiguration authConfiguration;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        try {
            String systemId = request.getHeader("id");
            String systemCode = request.getHeader("systemCode");
            if (systemId == null){
                handleFalseResponse(response);
                return false;
            }
            Map<String, Object> systemMap = authConfiguration.matchNewObject(systemId);
            Object code = systemMap.get("systemCode");
            boolean judge = code.equals(systemCode);
            if (!judge){
                handleFalseResponse(response);
            }
            return judge;
        } catch (Exception e){
            log.info(e.getMessage(), e);
            return false;
        }
    }

    private void handleFalseResponse(HttpServletResponse response)
            throws Exception {
        response.setStatus(500);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        response.getWriter().write("{\n" +
                "    \"code\": \"500\",\n" +
                "    \"msg\": \"身份验证失败！！！\"\n" +
                "}");
        response.getWriter().flush();
    }
}
