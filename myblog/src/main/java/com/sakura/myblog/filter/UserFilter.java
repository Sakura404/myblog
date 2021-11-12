package com.sakura.myblog.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author Sakura
 */
public class UserFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if ("GET".equals(((HttpServletRequest) servletRequest).getMethod())) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
            String token = ((HttpServletRequest) servletRequest).getHeader("LOGIN_TOKEN");
        }
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
