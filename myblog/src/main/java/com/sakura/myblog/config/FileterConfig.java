package com.sakura.myblog.config;

import com.sakura.myblog.filter.UserFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;

@Configuration
public class FileterConfig {
    @Bean
    public FilterRegistrationBean someFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(userFilter());
        registration.addUrlPatterns("/api/*");
        registration.setName("userFilter");
        registration.setOrder(Integer.MAX_VALUE);
        return registration;
    }
    @Bean
    public Filter userFilter(){
        return new UserFilter();
    }
}
