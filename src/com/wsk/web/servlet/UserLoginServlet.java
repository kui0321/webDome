package com.wsk.web.servlet;

import com.wsk.commons.Constants;
import com.wsk.exception.UserNotFoundException;
import com.wsk.pojo.Users;
import com.wsk.service.UserLoginService;
import com.wsk.service.impl.UserLoginServiceImpl;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login.do")
public class UserLoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String userpwd = req.getParameter("userpwd");
        String code = req.getParameter("code");

        try{
            HttpSession session = req.getSession();
            String codeTemp = (String)session.getAttribute(Constants.VALIDATE_CODE_KEY);
            if (codeTemp.equals(code)){
                UserLoginService userLoginService = new UserLoginServiceImpl();
                Users users = userLoginService.userLogin(username, userpwd);

                //建立客户端与服务端的会话状态
                session.setAttribute(Constants.USER_SESSION_KEY, users);

                ServletContext servletContext = this.getServletContext();
                HttpSession temp = (HttpSession) servletContext.getAttribute(users.getUserid()+"");

                if(temp != null){
                    servletContext.removeAttribute(users.getUserid()+"");
                    temp.invalidate();
                }

                servletContext.setAttribute(users.getUserid()+"",session);


                resp.sendRedirect("main.jsp");

            }else {
                req.setAttribute(Constants.REQUEST_MSG, "验证码输入有误，请重新输入  ");
                req.getRequestDispatcher("login.jsp").forward(req,resp);
            }


        }catch (UserNotFoundException e){
            req.setAttribute("msg", e.getMessage());
            req.getRequestDispatcher("login.jsp").forward(req, resp);
        }catch (Exception e){
            resp.sendRedirect("error.jsp");
        }

    }
}
