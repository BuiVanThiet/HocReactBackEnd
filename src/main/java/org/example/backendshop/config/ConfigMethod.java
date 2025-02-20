package org.example.backendshop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ConfigMethod implements WebMvcConfigurer  {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // Thêm cấu hình CORS cho phép frontend React (localhost:3000) gửi yêu cầu
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:3000")  // Cổng của frontend React
                .allowCredentials(true)  // Quan trọng: Cho phép gửi cookies
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS"); // Các phương thức yêu cầu
    }
}
