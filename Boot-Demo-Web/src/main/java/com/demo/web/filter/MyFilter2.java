package com.demo.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter(filterName = "myFilter2", urlPatterns = "/*")
public class MyFilter2 implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("MyFilter2...init2");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("MyFilter2...doFilter2");
        chain.doFilter(request,response);
    }

    @Override
    public void destroy() {
        System.out.println("MyFilter2...destroy2");
    }
}
