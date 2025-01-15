package com.harry.servlet.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.harry.pojo.Role;
import com.harry.pojo.User;
import com.harry.service.role.RoleService;
import com.harry.service.role.RoleServiceImpl;
import com.harry.service.user.UserService;
import com.harry.service.user.UserServiceImpl;
import com.harry.util.Constants;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("----User -> UserServlet----");

        String method = req.getParameter("method");
        if (method != null && method.equals("savePassword")) {
            this.changePassword(req, resp);
        } else if (method != null && method.equals("verifyPassword")) {
            this.verifyPassword(req, resp);
        } else if (method != null && method.equals("query")) {
            this.query(req, resp);
        } else if (method != null && method.equals("add")) {
            this.addUser(req, resp);
        } else if (method != null && method.equals("view")) {
            this.getUser(req, resp);
        } else if (method != null && method.equals("delete")) {
            this.deleteUser(req, resp);
        } else if (method != null && method.equals("update")) {
            this.updateUser(req, resp);
        } else if (method != null && method.equals("userExist")) {
            this.userExist(req, resp);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    // change password
    public void changePassword(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Object user = req.getSession().getAttribute(Constants.USER_SESSION);
        String newPassword = req.getParameter("newPassword");

        boolean flag = false;
        if (user != null && newPassword != null) {
            UserService userService = new UserServiceImpl();
            flag = userService.updatePwd(((User) user).getId(), newPassword);
            if (flag) {
                req.getSession().setAttribute("message", "Password change successful! Please login again!");
                // clear session and re login
                req.getSession().removeAttribute(Constants.USER_SESSION);
            } else {
                req.getSession().setAttribute("message", "Change failed! Try again!");

            }
        } else {
            req.getSession().setAttribute("message", "something went wrong!");
        }
        req.getRequestDispatcher("changePwd.jsp").forward(req, resp);
    }

    // verify current password
    public void verifyPassword(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        Object user = req.getSession().getAttribute(Constants.USER_SESSION);
        String currentPassword = req.getParameter("currentPassword");

        Map<String, String> resultMap = new HashMap<String, String>();

//        System.out.println(currentPassword);

        if (user == null) {
            resultMap.put("result", "sessionExpired");
        } else if (currentPassword.isEmpty()) {
            resultMap.put("result", "currentPasswordEmpty");
        } else {
            String userPassword = ((User) user).getUserPassword();
            if (currentPassword.equals(userPassword)) {
                resultMap.put("result", "pass");
            } else {
                resultMap.put("result", "currentPasswordWrong");
            }
        }
        // output as json format
        resp.setContentType("application/json");
        PrintWriter out = resp.getWriter();
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(resultMap);
        out.write(json);
        out.flush();
        out.close();
    }

    // query page -> user table
    public void query(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String queryUserName = req.getParameter("queryUserName");
        String queryUserRole = req.getParameter("queryUserRole");
        int queryUserRoleInt = 0;

        UserServiceImpl userService = new UserServiceImpl();

        // get user list
        if (queryUserName == null) {
            queryUserName = "";
        }
        if (queryUserRole != null && !queryUserRole.equals("")) {
            queryUserRoleInt = Integer.parseInt(queryUserRole);
        }
        List<User> users = userService.getUserList(queryUserName, queryUserRoleInt);
        System.out.println("queryUserName: " + queryUserName + ", queryUserROle: " + queryUserRoleInt);
        req.setAttribute("users", users);

        // get total user record
        int total = userService.totalUser(queryUserName, queryUserRoleInt);
        req.setAttribute("totalRecord", total);

        // get role list
        RoleService roleService = new RoleServiceImpl();
        List<Role> roles = roleService.getRolesList();
        req.setAttribute("roles", roles);

        req.setAttribute("queryUserName", queryUserName);
        req.setAttribute("queryUserRole", queryUserRole);

        req.getRequestDispatcher("userView.jsp").forward(req, resp);

    }

    // add new user
    public void addUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("User -> UserServlet -> add user");

        String userCode = req.getParameter("userCode");
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String userPassword = req.getParameter("userPassword");
        String confirmPassword = req.getParameter("confirmPassword");
        String gender = req.getParameter("gender");
        String phone = req.getParameter("phone");
        String address = req.getParameter("address");
        String userRole = req.getParameter("userRole");
        String birthMonth = req.getParameter("birthMonth");
        String birthDay = req.getParameter("birthDay");
        String birthYear = req.getParameter("birthYear");
        String birthday = birthYear + "/" + birthMonth + "/" + birthDay;

        User user = new User();
        user.setUserCode(userCode);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setUserPassword(userPassword);
        user.setGender(Integer.parseInt(gender));
        user.setBirthday(new Date(birthday));
        user.setPhone(phone);
        user.setAddress(address);
        user.setUserRole(Integer.parseInt(userRole));
        user.setCreationDate(new Date());
        user.setCreationBy(((User) req.getSession().getAttribute(Constants.USER_SESSION)).getId());

        UserService userService = new UserServiceImpl();
        if (userService.addUser(user)) {
            resp.sendRedirect(req.getContextPath() + "/jsp/user.do?method=query");
        } else {
            req.getRequestDispatcher("userAdd.jsp").forward(req, resp);
        }

    }

    // delete user
    public void deleteUser(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        System.out.println("User -> UserServlet -> delete user");

        String userCode = req.getParameter("userCode");
        System.out.println("User -> UserServlet -> delete " + userCode);

        // result json
        HashMap<String, String> resultMap = new HashMap<>();
        if (userCode != null && userCode.length() > 0) {
            UserService userService = new UserServiceImpl();
            if (userService.deleteUser(userCode)) {
                resultMap.put("result", "true");
            } else {
                resultMap.put("result", "fail");
            }
        } else {
            resultMap.put("result", "error");
        }

        resp.setContentType("application/json");
        PrintWriter out = resp.getWriter();
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(resultMap);
        out.write(json);
        out.flush();
        out.close();
    }

    // update user
    public void updateUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("User -> UserServlet -> update user");

        // update user object with to update info
        User user = new User();
        String gender = req.getParameter("gender");
        String phone = req.getParameter("phone");
        String address = req.getParameter("address");
        String userRole = req.getParameter("userRole");
        String birthMonth = req.getParameter("birthMonth");
        String birthDay = req.getParameter("birthDay");
        String birthYear = req.getParameter("birthYear");
        String birthday = birthYear + "/" + birthMonth + "/" + birthDay;
        user.setUserCode(req.getParameter("userCode"));
        user.setGender(Integer.parseInt(gender));
        user.setBirthday(new Date(birthday));
        user.setPhone(phone);
        user.setAddress(address);
        user.setUserRole(Integer.parseInt(userRole));
        user.setModifyDate(new Date());
        user.setModifyBy(((User) req.getSession().getAttribute(Constants.USER_SESSION)).getId());

        UserService userService = new UserServiceImpl();
        if (userService.updateUser(user)) {
            resp.sendRedirect(req.getContextPath() + "/jsp/user.do?method=query");
        } else {
            req.getRequestDispatcher("userUpdate.jsp").forward(req, resp);
        }

    }

    // check if user exists
    public void userExist(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("User -> UserServlet -> userExist");

        String userCode = req.getParameter("userCode");
        HashMap<String, String> resultMap = new HashMap<>();
        if (userCode != null && !userCode.equals("")) {
            UserService userService = new UserServiceImpl();
            User user = userService.login(userCode, "");
            if (user != null) {
                resultMap.put("result", "yes");
            } else {
                resultMap.put("result", "no");
            }
        }
        // send result jason
        resp.setContentType("application/json");
        PrintWriter out = resp.getWriter();
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(resultMap);
        out.write(json);
        out.flush();
        out.close();
    }

    // get user record by userCode
    public void getUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("User -> UserServlet -> getUser");

        String userCode = req.getParameter("userCode");
        UserService userService = new UserServiceImpl();
        User user = userService.getUser(userCode);

        System.out.println(user);
        req.setAttribute("user", user);
        req.getRequestDispatcher("userRecord.jsp").forward(req, resp);
    }

}
