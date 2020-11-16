package com.demo.web.config;

import com.demo.web.filter.MyFilter;
import com.demo.web.filter.MyFilter2;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean<MyFilter> getFilterRegistrationBean(){
        FilterRegistrationBean<MyFilter> bean = new FilterRegistrationBean<MyFilter>(new MyFilter());
        bean.setOrder(1);
        return bean;
    }

    @Bean
    public FilterRegistrationBean getFilterRegistrationBean2(){
        FilterRegistrationBean bean = new FilterRegistrationBean<>(new MyFilter2());
        bean.setOrder(2);
        return bean;
    }
}
