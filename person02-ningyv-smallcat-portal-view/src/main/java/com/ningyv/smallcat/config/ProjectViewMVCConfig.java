package com.ningyv.smallcat.config;

import com.ningyv.smallcat.interceptor.HandlerInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author LCX
 * @create 2020-01-04 20:00
 */
@Configuration
public class ProjectViewMVCConfig implements WebMvcConfigurer {

    @Autowired
    private HandlerInterceptor handlerInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry
                .addInterceptor(handlerInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/project/get/member/info/by/token.html")
                .excludePathPatterns("/portal.html")
                .excludePathPatterns("/css/**")
                .excludePathPatterns("/images/**")
                .excludePathPatterns("/jquery/**")
                .excludePathPatterns("/js/**")
                .excludePathPatterns("/layer/**")
                .excludePathPatterns("/img/**")
                .excludePathPatterns("/svg/**")
                .excludePathPatterns("/bootstrap/**")
                .excludePathPatterns("/font-awesome/**")
                .excludePathPatterns("/highlight.js/**")
                .excludePathPatterns("/jspdf/**")
                .excludePathPatterns("/plupload/**")
                .excludePathPatterns("/zeroclipboard/**")
                .excludePathPatterns("/fonts/**")
                .excludePathPatterns("/ravenjs/**")
                .excludePathPatterns("/videojs/**")
                .excludePathPatterns("/app/**")
                .excludePathPatterns("/ace/**")
                .excludePathPatterns("/bootstrap-table/**")
                .excludePathPatterns("/bootstrap-tour/**")
                .excludePathPatterns("/to/privacy.html")
                .excludePathPatterns("/stylesheets/**")
                .excludePathPatterns("/")
                .excludePathPatterns("/member/do/login.html")
                .excludePathPatterns("/member/sent/short/message.html")
                .excludePathPatterns("/member/to/register.html")
                .excludePathPatterns("/back/portal.html")
                .excludePathPatterns("/forget/password.html")
                .excludePathPatterns("/to/member/register.html")
                .excludePathPatterns("/other/error.html")
                .excludePathPatterns("/member/to/center.html")
                .excludePathPatterns("/layui/**")
                .excludePathPatterns("/error.html")
                .excludePathPatterns("/defaultKaptcha")
                .excludePathPatterns("/favicon.ico");

    }
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/error.html").setViewName("error");
    }
}
