package com.demo.system.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * 后端跨域配置
 * @Author Sheva
 * @Date 2021/2/24 13:09
 */
@Slf4j
@Configuration
public class GlobalCorsConfig {

    @Value("${allowed.origin}")
    private String allowedOrigin;
    @Bean
    public CorsFilter corsFilter(){
        log.info("允许跨域： " + this.allowedOrigin);
        // 添加CORS配置信息
        CorsConfiguration config = new CorsConfiguration();
        // 允许的域，不要写*，否则cookie无法使用
        config.addAllowedOrigin(allowedOrigin);
        // 是否发送Cookie信息
        config.setAllowCredentials(true);
        // 允许的请求方式
        config.addAllowedMethod("*");
        config.addAllowedHeader("*");
        // 添加映射路径，拦截一切请求
        UrlBasedCorsConfigurationSource configSource = new UrlBasedCorsConfigurationSource();
        configSource.registerCorsConfiguration("/**", config);
        // 返回新的CorsFilter
        return new CorsFilter(configSource);
    }
}
