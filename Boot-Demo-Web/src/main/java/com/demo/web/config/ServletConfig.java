package com.demo.web.config;

import com.demo.web.servlet.MyServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *自定义servlet
 */
@Configuration
public class ServletConfig {


    @Bean
    public ServletRegistrationBean<MyServlet> getServletRegistrationBean(){
        // 注意此处 ServletRegistrationBean 构造方法中的urlMapping传参不能少，否则 controller 中的urlMapping 将无法映射
        ServletRegistrationBean<MyServlet> bean = new ServletRegistrationBean<>(new MyServlet(),"/demo/area/getById/100");
        bean.setLoadOnStartup(1);
        return bean;
    }
}
