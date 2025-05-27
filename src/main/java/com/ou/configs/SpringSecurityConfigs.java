package com.ou.configs;

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
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

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
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(c -> c.disable())
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
