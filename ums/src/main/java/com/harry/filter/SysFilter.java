package com.harry.filter;

import com.harry.pojo.User;
import com.harry.util.Constants;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SysFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }

    // block no session visit
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        // get user info from session
        User user = (User) request.getSession().getAttribute(Constants.USER_SESSION);
        if (user == null) {
            // no session, do something
            response.sendRedirect(request.getContextPath());
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }
}
