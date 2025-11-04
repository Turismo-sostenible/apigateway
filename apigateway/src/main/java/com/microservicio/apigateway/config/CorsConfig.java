package com.microservicio.apigateway.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import java.util.Arrays;

@Configuration
public class CorsConfig {

    @Bean
    public CorsWebFilter corsWebFilter() {

        CorsConfiguration corsConfig = new CorsConfiguration();
        
        // --- URLS PERMITIDAS ---
        // Aquí debes poner tus URLs de frontend
        corsConfig.setAllowedOrigins(Arrays.asList(
            "http://localhost:3000",
            "https://frontend-santabarbara-m.vercel.app" // Tu URL de Vercel
        ));
        
        
        corsConfig.setMaxAge(3600L); // 1 hora
        corsConfig.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        corsConfig.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type,","tenant_id"));
        corsConfig.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfig); // Aplica a todas las rutas

        return new CorsWebFilter(source);
    }
}