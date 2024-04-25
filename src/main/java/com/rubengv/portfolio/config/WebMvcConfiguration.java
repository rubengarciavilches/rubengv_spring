package com.rubengv.portfolio.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {
//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/**")
//                .allowedHeaders("*") // *
//                .exposedHeaders("*") // *
//                .allowedMethods("GET", "POST", "PUT", "DELETE") // *
//                .allowedOrigins("http://localhost:3000", "https://www.rubengv.com") // *
//                .allowCredentials(true);  // false
//    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedHeaders("*") // *
                .exposedHeaders("*") // *
                .allowedMethods("GET", "POST", "PUT", "DELETE") // *
                .allowedOrigins("*") // *
                .allowCredentials(false);  // false
    }
}
