package com.harry.dao.user;

import com.harry.pojo.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface UserDao {

    // get user info
    public User getLoginUser(Connection connection, String userCode) throws SQLException;

    // update current user password
    public int updatePwd(Connection connection, int id, String password) throws SQLException;

    // total user number
    public int totalUser(Connection connection, String userCode, int userRole) throws SQLException;

    // get user list
    public List<User> getUserList(Connection connection, String userCode, int userRole) throws SQLException;

    // add new user
    public int addUser(Connection conn, User user) throws SQLException;

    // delete user by userCode
    public int deleteUser(Connection conn, String userCode) throws SQLException;

    // update user info
    public int updateUser(Connection conn, User user) throws SQLException;
}