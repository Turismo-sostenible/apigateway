package com.microservicio.apigateway.config;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        
        String[] allowedOrigins = {
            "http://localhost:3000", 
            "https://frontend-santabarbara-m.vercel.app" 
            // Añade aquí tu URL de producción de Vercel
        };

        registry.addMapping("/**") 
            .allowedOrigins(allowedOrigins) 
            .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") 
            .allowedHeaders("Authorization", "Content-Type") 
            .allowCredentials(true); 
    }
}