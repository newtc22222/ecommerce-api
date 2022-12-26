package com.hcmute.ecom.config;

import com.hcmute.ecom.filter.URLFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Nhat Phi
 * @since 2022-11-22
 * This class use to log request to terminal
 * */
@Configuration
public class FilterConfig {
    @Bean
    public FilterRegistrationBean<URLFilter> filterFilterRegistrationBean() {
        FilterRegistrationBean<URLFilter> registrationBean = new FilterRegistrationBean<>();

        registrationBean.setFilter(new URLFilter());
        registrationBean.setOrder(1);
        return registrationBean;
    }
}
