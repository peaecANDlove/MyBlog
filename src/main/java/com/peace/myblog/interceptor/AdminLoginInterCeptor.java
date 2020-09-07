package com.peace.myblog.interceptor;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author YR#
 * @create 2020-08-14 12:32
 */
public class AdminLoginInterCeptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if (request.getSession().getAttribute("userModel") == null) {
            response.sendRedirect("/admin");
            return false;
        }

        return true;
    }
}
