package cn.rs.picwall.config;

import cn.rs.picwall.util.IpUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;

/**
 * This interceptor is provided to control the request ip client
 */
//@Component
public class IpInterceptor implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(IpInterceptor.class);

//    @Value("${ip.whitelist}")
    private String ipWhitelist;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String ip = IpUtil.getRealIP(request);
        logger.info("Request ip is {}", ip);

        List<String> whiteList = Arrays.asList(ipWhitelist.split(","));
        if (whiteList.contains(ip)) {
            return true;
        } else {
            response.getWriter().append("<h1 style=\\\"text-align:center;\\\">Not Allowed!</h1>");
            return false;
        }
    }
}
