package com.ou.configs;

import com.ou.filters.JwtFilter;
import jakarta.servlet.Filter;
import jakarta.servlet.MultipartConfigElement;
import jakarta.servlet.ServletRegistration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
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
        long maxFileSize = 10485760; // 10MB
        long maxRequestSize = 20971520; // 20MB
        int fileSizeThreshold = 0;

        registration.setMultipartConfig(new MultipartConfigElement(location, maxFileSize, maxRequestSize, fileSizeThreshold));
    }

//    // Dung CustomUserDetails, nen o JwtFilter dung UserDetailsService
      //    nen phai bien JwtFilter thanh bean, nhung o day khong wire vao duoc, nen lay thu cong tu AppContext => Failed

//    // Cach tren khong duoc nen chuyen JwtFilter sang SpringSecurityConfigs ==> OK

//    @Override
//    protected Filter[] getServletFilters() {
//
//        // lấy ApplicationContext của Spring
//        AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
//        ctx.register(SpringSecurityConfigs.class);
//
//        // lấy JwtFilter từ context
//        JwtFilter jwtFilter = ctx.getBean(JwtFilter.class);
//
//        return new Filter[]{ jwtFilter };
//    }
}
