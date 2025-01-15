package com.harry.servlet.user;

import com.harry.pojo.User;
import com.harry.service.user.UserServiceImpl;
import com.harry.util.Constants;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("----User -> LoginServlet----");

        // get login info from request
        String userCode = req.getParameter("userCode");
        String password = req.getParameter("password");

        UserServiceImpl userService = new UserServiceImpl();
        User user = userService.login(userCode, password);

        if (user != null && user.getUserPassword().equals(password)) {
            // user session
            req.getSession().setAttribute(Constants.USER_SESSION, user);
            resp.sendRedirect(req.getContextPath() + "/jsp/home.jsp");

        } else {
            req.setAttribute("error", "Wrong Username or Password");
            req.getRequestDispatcher("login.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
