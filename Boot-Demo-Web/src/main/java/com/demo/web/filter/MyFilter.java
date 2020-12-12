package com.demo.web.filter;



import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter(filterName = "myFilter",urlPatterns = "/*")
public class MyFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
//        System.out.println("MyFilter...init...");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("MyFilter...doFilter...");
        filterChain.doFilter(request,response);
    }

    @Override
    public void destroy() {
//        System.out.println("MyFilter...destroy...");
    }
}
