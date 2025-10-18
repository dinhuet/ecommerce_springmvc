package com.dinh.todo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.File;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // map URL /images/** đến thư mục uploads/avatar/
        String path = System.getProperty("user.dir") + "/ecommerce/todo/uploads/";
        registry.addResourceHandler("/images/**")
                .addResourceLocations("file:" + path);
        System.out.println("Resource path: " + path);

    }
}
