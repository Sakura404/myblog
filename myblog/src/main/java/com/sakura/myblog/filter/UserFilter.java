package com.sakura.myblog.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author Sakura
 */
@WebFilter(urlPatterns = "/api/*", filterName = "userFilter")
public class UserFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println(1);
        if ("GET".equals(((HttpServletRequest) servletRequest).getMethod())) {
            for (Cookie cookie:
                 ((HttpServletRequest) servletRequest).getCookies()){
                System.out.println(cookie.getValue());
            }

            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            String token = ((HttpServletRequest) servletRequest).getHeader("LOGIN_TOKEN");
            System.out.println(token);
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
