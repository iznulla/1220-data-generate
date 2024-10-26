package com.uzinfo.datagenerate.web.configuration.datasource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig {
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**") // применить ко всем путям
                        .allowedOrigins("http://localhost:8080") // разрешить доступ для нужного домена
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // разрешить методы
                        .allowedHeaders("*") // разрешить любые заголовки
                        .allowCredentials(true); // разрешить отправку куки
            }
        };
    }
}
