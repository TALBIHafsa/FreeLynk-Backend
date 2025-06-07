package org.example.freelynk.config;

import java.util.Arrays;

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
                System.out.println("Configuring CORS mappings...");
                registry.addMapping("/**") 
                        .allowedOrigins("http://localhost:3000")
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS", "HEAD", "PATCH")
                        .allowedHeaders(
                            "Authorization",
                            "Content-Type", 
                            "Accept",
                            "Origin",
                            "X-Requested-With",
                            "Access-Control-Request-Method",
                            "Access-Control-Request-Headers"
                        )
                        .exposedHeaders(
                            "Authorization",
                            "Access-Control-Allow-Origin",
                            "Access-Control-Allow-Credentials"
                        )
                        .allowCredentials(true);
                System.out.println("CORS configuration applied for localhost:3000 with Authorization header support");
            }
        };
    }
}