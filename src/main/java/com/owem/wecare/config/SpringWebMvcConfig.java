package com.owem.wecare.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Owem
 * @date 2022/11/23 18:50
 * @description TODO
 **/
@Service
public class SpringWebMvcConfig implements WebMvcConfigurer {
    @Value("${userSetting.localSrc}")
    String localSrc;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/share/**").addResourceLocations("file:" + localSrc);
    }
}
