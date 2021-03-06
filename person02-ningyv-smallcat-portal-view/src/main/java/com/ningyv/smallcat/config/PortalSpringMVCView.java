package com.ningyv.smallcat.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author LCX
 * @create 2019-12-23 20:23
 */
@Configuration
public class PortalSpringMVCView implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/to/member/register.html").setViewName("menber-register");
        registry.addViewController("/back/portal.html").setViewName("portal");
        registry.addViewController("/member/to/center.html").setViewName("member-conter");
    }
}
