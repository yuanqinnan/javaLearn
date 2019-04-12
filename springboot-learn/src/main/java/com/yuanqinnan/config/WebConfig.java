package com.yuanqinnan.config;

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

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/yuan").setViewName("success");
    }
}
