package com.sakura.myblog.filter;

import com.sakura.myblog.constant.enums.LoginResponseEnum;
import com.sakura.myblog.model.dto.LoginException;
import com.sakura.myblog.utils.TokenUtil;
import io.jsonwebtoken.Claims;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
        String url = ((HttpServletRequest) servletRequest).getRequestURI();
        if (!"/api/users/login".equals(url) && !"/api/comments/".equals(url)) {
            if (!"GET".equals(((HttpServletRequest) servletRequest).getMethod())) {
                Cookie cookie = getCookie(((HttpServletRequest) servletRequest), "LOGIN_TOKEN");
                if (cookie == null) {
                    throw new LoginException(LoginResponseEnum.NOT_LOGIN.getCode(), LoginResponseEnum.NOT_LOGIN.getMsg());
                }
                try {
                    String token = cookie.getValue();
                    Claims claims = TokenUtil.parseJWT(token);
                } catch (Exception e) {
                    cookie.setMaxAge(20);
                    ((HttpServletResponse) servletResponse).addCookie(cookie);
                    throw new LoginException(LoginResponseEnum.NOT_LOGIN.getCode(), LoginResponseEnum.NOT_LOGIN.getMsg());
                }
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }

    public Cookie getCookie(HttpServletRequest request, String key) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(key)) {
                    return cookie;
                }
            }
        }
        return null;
    }
}
