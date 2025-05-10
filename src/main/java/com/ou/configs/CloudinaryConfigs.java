package com.ou.configs;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Configuration
@PropertySource("classpath:application.properties")
public class CloudinaryConfigs {

    @Autowired
    private Environment env;

    @Bean
    public Cloudinary cloudinary() {
        Cloudinary cloudinary = new Cloudinary(
                ObjectUtils.asMap(
                        "cloud_name", env.getProperty("cloudinary.name") ,
                        "api_key", env.getProperty("cloudinary.api_key"),
                        "api_secret", env.getProperty("cloudinary.api_secret"),
                        "secure", true));
        return cloudinary;
    }
}
