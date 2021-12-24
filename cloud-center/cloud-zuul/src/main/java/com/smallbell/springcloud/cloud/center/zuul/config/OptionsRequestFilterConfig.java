package com.smallbell.springcloud.cloud.center.zuul.config;

import com.smallbell.springcloud.common.constants.SessionConstants;
import com.smallbell.springcloud.standard.filter.OptionsRequestFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OptionsRequestFilterConfig
{

    @Bean
    public FilterRegistrationBean buildOptionsRequestFilter()
    {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setOrder(SessionConstants.OPTIONS_REQUEST_ORDER);
        filterRegistrationBean.setFilter(new OptionsRequestFilter());
        filterRegistrationBean.setName("optionsRequestFilter" );
        filterRegistrationBean.addUrlPatterns("/*" );
        return filterRegistrationBean;
    }



}