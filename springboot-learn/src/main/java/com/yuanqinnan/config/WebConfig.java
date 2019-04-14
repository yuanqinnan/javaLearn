package com.yuanqinnan.config;

import com.yuanqinnan.filter.LoginHandlerInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Description: java-learn
 * <p>
 * Created by yuanqn on 2019/4/12 21:42
 */

//@EnableWebMvc
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Autowired
    private LoginHandlerInterceptor loginHandlerInterceptor;

//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(loginHandlerInterceptor).addPathPatterns("/**")
//                .excludePathPatterns("/index.html","/","/user/login");
//    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/yuan").setViewName("success");
        registry.addViewController("/").setViewName("login");
        registry.addViewController("/index.html").setViewName("login");
        registry.addViewController("/main.html").setViewName("index");
    }
}
