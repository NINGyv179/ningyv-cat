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
                .excludePathPatterns("/")
                .excludePathPatterns("/member/do/login.html")
                .excludePathPatterns("/member/sent/short/message.html")
                .excludePathPatterns("/member/to/register.html")
                .excludePathPatterns("/back/portal.html")
                .excludePathPatterns("/forget/password.html")
                .excludePathPatterns("/to/member/register.html")
                .excludePathPatterns("/other/error.html")
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
