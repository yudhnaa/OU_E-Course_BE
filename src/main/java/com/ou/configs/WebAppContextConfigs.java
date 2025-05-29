/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ou.configs;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.ou.formatters.*;
import com.ou.formatters.CourseFormatter;
import com.ou.formatters.UserFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.format.FormatterRegistry;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

import java.util.Locale;

/**
 *
 * @author yudhna
 */
@Configuration
@EnableWebMvc
@EnableTransactionManagement
@ComponentScan(basePackages = {
    "com.ou.controllers",
    "com.ou.repositories",
    "com.ou.services",
    "com.ou.formatters",
    "com.ou.helpers",
    "com.ou.mappers",
    "com.ou.utils"

})
public class WebAppContextConfigs implements WebMvcConfigurer {
    @Autowired
    private UserFormatter userFormatter;
    @Autowired
    private CourseFormatter courseFormatter;

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {

        configurer.enable();
    }

    /*
     * This method configures the LocaleResolver for handling locale changes.
     * It sets the default locale and the cookie name for storing the locale.
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        // Add the LocaleChangeInterceptor to the registry for locale change handling
        registry.addInterceptor(localeChangeInterceptor());
    }

    /*
     * This method configures the LocaleChangeInterceptor for handling locale changes.
     * It sets the parameter name for the locale change request parameter.
     */
    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor interceptor = new LocaleChangeInterceptor();
        interceptor.setParamName("lang");
        return interceptor;
    }

    /*
     * This method configures the LocaleResolver for handling locale changes.
     * It sets the default locale, cookie name, and cookie expiration time.
     * The default locale is set to English.
     * This is used in LocaleChangeInterceptor
     */
    @Bean
    public LocaleResolver localeResolver() {
        CookieLocaleResolver resolver = new CookieLocaleResolver();
        resolver.setDefaultLocale(Locale.ENGLISH); // Default locale
        resolver.setCookieName("lang"); // Cookie name
        resolver.setCookieMaxAge(9999); // Cookie expiration in seconds
        return resolver;
    }

    @Bean
    public StandardServletMultipartResolver multipartResolver() {
        return new StandardServletMultipartResolver();
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/assets/**").addResourceLocations("classpath:/static/assets/");
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addFormatter(new ExerciseFormatter());
        registry.addFormatter(new QuestionTypeFormatter());
        registry.addFormatter(new LessonFormatter());
        registry.addFormatter(new ExerciseScoreStatusFormatter());
        registry.addFormatter(new ExerciseAttemptFormatter());
        registry.addFormatter(new LecturerFormatter());
        registry.addFormatter(new StudentFormatter());
        registry.addFormatter(new TestAttemptFormatter());
        registry.addFormatter(new TestFormatter());
        registry.addFormatter(userFormatter);
        registry.addFormatter(courseFormatter);
    }

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        return mapper;
    }
}
