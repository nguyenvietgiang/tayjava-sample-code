package vn.tayjava.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true); // Cho phép gửi cookie
        config.addAllowedOriginPattern("*"); // Cho phép tất cả domain
        config.addAllowedHeader("*"); // Cho phép tất cả header
        config.addAllowedMethod("*"); // Cho phép tất cả method (GET, POST, PUT, DELETE, etc.)

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config); // Áp dụng cho tất cả URL
        return new CorsFilter(source);
    }
}

