package org.example.backendshop.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final UserAuthenticationEntryPoint userAuthenticationEntryPoint;
    private final UserAuthProvider userAuthProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .exceptionHandling().authenticationEntryPoint(userAuthenticationEntryPoint)
                .and()
                .addFilterBefore(new JwtAuthFilter(userAuthProvider), BasicAuthenticationFilter.class)
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers(HttpMethod.POST, "/login", "/register","/logout-account").permitAll()
                        .requestMatchers("/product-manage/get-all","/product-manage/product/*","/product-manage/image_product/*").permitAll()
                        .requestMatchers("/product-detail-manage/all-product-detail/*","/product-detail-manage/price-seling-product/*/*/*").permitAll()
                        .requestMatchers(HttpMethod.POST, "/product-manage/upload_image_product").authenticated()
                        //attribute
                        .requestMatchers(HttpMethod.GET,"/color-manage/all-object","/color-manage/color-by-product/*").permitAll()
                        .requestMatchers(HttpMethod.GET,"/category-manage/all-object").permitAll()
                        .requestMatchers(HttpMethod.GET,"/manufacturer-manage/all-object").permitAll()
                        .requestMatchers(HttpMethod.GET,"/origin-manage/all-object").permitAll()
                        .requestMatchers(HttpMethod.GET,"/size-manage/all-object","/size-manage/size-by-product/*").permitAll()
                        .requestMatchers(HttpMethod.GET,"/weight-type-manage/all-object").permitAll()
                        .requestMatchers("/ws/**").permitAll() // Mở WebSocket
                        .anyRequest().authenticated())
        ;
        return http.build();
    }
}
