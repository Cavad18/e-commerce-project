package com.example.productShopping.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return addCorsMappings((registry) -> {
            registry.addMapping("/**")
                    .allowedOrigins("http://127.0.0.1:5500", "http://localhost:5500")
                    .allowedMethods("GET", "POST", "DELETE", "OPTIONS")
                    .allowedHeaders("*")
                    .allowedCredentials(true);
        });
    }
}
