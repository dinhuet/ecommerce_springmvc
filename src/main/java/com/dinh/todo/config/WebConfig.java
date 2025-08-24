package com.dinh.todo.config;

import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // map URL /images/** đến thư mục uploads/avatar/
        registry.addResourceHandler("/images/**")
                .addResourceLocations("file:uploads/avatar/");
    }
}
