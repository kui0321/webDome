package com.wsk.web.filter;


import com.wsk.commons.Constants;
import com.wsk.pojo.Users;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = {"*.jsp","*.do"})
public class UserLoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String uri = request.getRequestURI();

        if(uri.indexOf("login.jsp") != -1 || uri.indexOf("login.do") != -1 || uri.indexOf("validateCode.do") != -1){
            filterChain.doFilter(servletRequest, servletResponse);
        }else {
            HttpSession session = request.getSession();
            Users users =(Users)session.getAttribute(Constants.USER_SESSION_KEY);
            if (users != null){
                filterChain.doFilter(servletRequest,servletResponse);
            }else {
                request.setAttribute(Constants.REQUEST_MSG, "需要登录");
                request.getRequestDispatcher("login.jsp").forward(servletRequest, servletResponse);
            }
        }


    }
}
