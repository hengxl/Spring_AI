package com.hxl.spring.ai.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.format.datetime.standard.DateTimeFormatterRegistrar;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.time.format.DateTimeFormatter;

/**
 * Web配置
 *
 * @author hxl
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    /**
     * 配置跨域访问规则（解决前后端跨域问题）
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
                 // 对所有接口生效
        registry.addMapping("/**")
                // 允许所有域名跨域
                .allowedOriginPatterns("*")
                .allowedHeaders("*")
                .allowedMethods("GET", "HEAD", "POST", "PUT", "DELETE", "OPTIONS")
                // 允许携带Cookie等凭证
                .allowCredentials(true)
                // 预检请求（OPTIONS）的缓存时间，1小时内无需重复预检
                .maxAge(3600);
    }

    /**
     * 配置全局日期格式化规则
     * 作用：统一处理前端传入/后端返回的日期格式（yyyy-MM-dd HH:mm:ss）
     */
    @Override
    public void addFormatters(FormatterRegistry registry) {
        DateTimeFormatterRegistrar registrar = new DateTimeFormatterRegistrar();
        // 设置全局日期时间格式化模板
        registrar.setDateTimeFormatter(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        // 注册到格式化器注册表，全局生效
        registrar.registerFormatters(registry);
    }
}
