package com.ou.configs;

import jakarta.servlet.MultipartConfigElement;
import jakarta.servlet.ServletRegistration;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class DispatcherServletInit extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{
            ThymeleafConfigs.class,
            HibernateConfigs.class,
            SpringSecurityConfigs.class,
            CloudinaryConfigs.class,
            MessageSourceConfig.class,
            GoogleDriveConfigs.class
        };
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[] {WebAppContextConfigs.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[] {"/"};
    }

    @Override
    protected void customizeRegistration(ServletRegistration.Dynamic registration) {
        String location = "/tmp";
        long maxFileSize = 5242880; // 5MB
        long maxRequestSize = 20971520; // 20MB
        int fileSizeThreshold = 0;

        registration.setMultipartConfig(new MultipartConfigElement(location, maxFileSize, maxRequestSize, fileSizeThreshold));
    }
}
