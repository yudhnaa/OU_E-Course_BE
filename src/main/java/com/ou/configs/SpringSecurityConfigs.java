package com.ou.configs;

import com.ou.filters.JwtFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableWebSecurity
@EnableTransactionManagement
@ComponentScan(basePackages = {
        "com.ou.controllers",
        "com.ou.repositories",
        "com.ou.services",
        "com.ou.formatters",
        "com.ou.helpers",
        "com.ou.mappers",
        "com.ou.utils",
        "com.ou.filters"
})
public class SpringSecurityConfigs {
    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public HandlerMappingIntrospector mvcHandlerMappingIntrospector() {
        return new HandlerMappingIntrospector();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();

        config.setAllowedOrigins(List.of("http://localhost:3000")); // neu co credentials, thi khong duoc dung "*"
        config.setAllowCredentials(true);
        config.setAllowedHeaders(List.of("Authorization", "Content-Type")); // dung jwt, nen can Authorization header (quan trong vai luon, khong la loi preflight ben react)
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

        source.registerCorsConfiguration("/**", config);

        return source;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, JwtFilter jwtFilter) throws Exception {
        http
                .cors(c -> c.configurationSource(corsConfigurationSource()))
//                Chuyen JwtFilter sang day
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .csrf(c -> c.disable())
                .authorizeHttpRequests(requests -> requests
                                .requestMatchers("/assets/**").permitAll()
                                .requestMatchers("/register").permitAll()

                                .requestMatchers(
                                        "/admin/users/**",
                                        "/admin/user/**",
                                        "/admin/lecturers",
                                        "/admin/lecturer",
                                        "/admin/students",
                                        "/admin/student",
                                        "/admin/course/create"
                                ).hasRole("ADMIN")

                                .requestMatchers(
                                        "/admin/courses/**",
                                        "/admin/course/**",
                                        "/admin/student-certificates",
                                        "/profile"
                                ).hasAnyRole("ADMIN", "LECTURER")

                                .requestMatchers("/access-denied").permitAll()

                                .requestMatchers("/api/**").permitAll()

                                .anyRequest().hasAnyRole("LECTURER", "ADMIN")
                )
                .formLogin(form
                        -> form.loginPage("/login")
                        .loginProcessingUrl("/login")
                        .defaultSuccessUrl("/")
                        .failureUrl("/login").permitAll())

                .logout(logout -> logout.logoutSuccessUrl("/login").permitAll())

                .exceptionHandling(exception -> exception
                        .accessDeniedPage("/access-denied")
                );

        return http.build();
    }
}
