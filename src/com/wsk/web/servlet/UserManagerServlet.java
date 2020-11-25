package com.wsk.web.servlet;

import com.wsk.pojo.Users;
import com.wsk.service.UserManagerService;
import com.wsk.service.impl.UserManagerServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/userManager.do")
public class UserManagerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String flag = req.getParameter("flag");
        if ("addUser".equals(flag)){
            this.addUser(req, resp);
        }
        else if("findUser".equals(flag)){
            this.findUser(req, resp);
        }
        else if ("preUpdate".equals(flag)){
            this.preUpdate(req, resp);
        }
        else if ("modifyUser".equals(flag)){
            this.modifyUser(req, resp);
        }
        else{
            this.dropUser(req, resp);
        }
    }



    //处理添加用户请求
    protected void addUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Users users = this.createUsers(req);
        try{
            UserManagerService userManagerService = new UserManagerServiceImpl();
            userManagerService.addUser(users);
            resp.sendRedirect("ok.jsp");

        }catch (Exception e){
            e.printStackTrace();
            resp.sendRedirect("error.jsp");
        }
    }

    //获取用户提交数据
    private Users createUsers(HttpServletRequest req){
        String username = req.getParameter("username");
        String userpwd = req.getParameter("userpwd");
        String usersex = req.getParameter("usersex");
        String phonenumber = req.getParameter("phonenumber");
        String qqnumber = req.getParameter("qqnumber");

        Users users = new Users();
        users.setUsername(username);
        users.setUserpwd(userpwd);
        users.setUsersex(usersex);
        users.setPhonenumber(phonenumber);
        users.setQqnumber(qqnumber);
        return users;
    }

    /**
     * 处理查询用户请求
     */
    private void findUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Users user = this.createUsers(req);
        try{
            UserManagerService userManagerService = new UserManagerServiceImpl();
            List<Users> list = userManagerService.findUser(user);
            req.setAttribute("list", list);

            req.getRequestDispatcher("usermanager/viewUser.jsp").forward(req, resp);

        }catch (Exception e){
            e.printStackTrace();
            resp.sendRedirect("error.jsp");
        }
    }

    /**
     * 创建更新的lvelet
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    private void preUpdate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userid = req.getParameter("userid");
        try{
            UserManagerService userManagerService = new UserManagerServiceImpl();
            Users user = userManagerService.findUserByUserid(Integer.parseInt(userid));
            req.setAttribute("user", user);
            req.getRequestDispatcher("usermanager/updateUser.jsp").forward(req, resp);
        }catch (Exception e){
            e.printStackTrace();
            resp.sendRedirect("error.jsp");
        }
    }

    /**
     * 处理更新数据请求
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    private void modifyUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String userid =req.getParameter("userid");
        Users users = this.createUsers(req);
        users.setUserid(Integer.parseInt(userid));
        try{
            UserManagerService userManagerService = new UserManagerServiceImpl();
            userManagerService.modifyUser(users);
            resp.sendRedirect("ok.jsp");
        }catch (Exception e){
            e.printStackTrace();
            resp.sendRedirect("error.jsp");
        }
    }

    /**
     * 处理删除业务请求
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    private void dropUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userid = req.getParameter("userid");
        try{
            UserManagerService userManagerService = new UserManagerServiceImpl();
            userManagerService.dropUser(Integer.parseInt(userid));
            resp.sendRedirect("ok.jsp");
        }catch (Exception e){
            e.printStackTrace();
            resp.sendRedirect("error.jsp");
        }
    }
}
