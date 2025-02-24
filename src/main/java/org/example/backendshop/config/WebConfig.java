package org.example.backendshop.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.Arrays;

/**
 * Cấu hình CORS cho ứng dụng Spring Boot
 */
@Configuration
@EnableWebMvc
public class WebConfig {

    // Thời gian tối đa mà trình duyệt lưu cache CORS (tính bằng giây)
    private static final Long MAX_AGE = 3600L;

    // Mức độ ưu tiên của CORS filter (cao hơn Spring Security)
    private static final int CORS_FILTER_ORDER = -102;

    @Bean
    public FilterRegistrationBean<CorsFilter> corsFilter() {
        // Tạo nguồn cấu hình CORS
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();

        // Cho phép gửi thông tin xác thực (cookies, headers)
        config.setAllowCredentials(true);

        // Chỉ cho phép request từ React chạy trên localhost:3000
        config.addAllowedOrigin("http://localhost:3000");

        // Chỉ cho phép các header sau:
        config.setAllowedHeaders(Arrays.asList(
                HttpHeaders.AUTHORIZATION, // Dùng cho xác thực token
                HttpHeaders.CONTENT_TYPE,  // Xác định kiểu dữ liệu gửi đi
                HttpHeaders.ACCEPT         // Xác định kiểu dữ liệu nhận về
        ));

        // Chỉ cho phép các phương thức HTTP sau:
        config.setAllowedMethods(Arrays.asList(
                HttpMethod.GET.name(),    // Lấy dữ liệu
                HttpMethod.POST.name(),   // Gửi dữ liệu
                HttpMethod.PUT.name(),    // Cập nhật dữ liệu
                HttpMethod.DELETE.name()  // Xóa dữ liệu
        ));

        // Đặt thời gian cache của CORS (1 giờ)
        config.setMaxAge(MAX_AGE);

        // Áp dụng cấu hình CORS cho tất cả các đường dẫn trong API
        source.registerCorsConfiguration("/**", config);

        // Đăng ký CORS filter
        FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<>(new CorsFilter(source));

        // Đặt thứ tự chạy của filter trước Spring Security
        bean.setOrder(CORS_FILTER_ORDER);

        return bean;
    }
}
