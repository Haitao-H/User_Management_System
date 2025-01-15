package com.harry.service.user;

import com.harry.pojo.User;

import java.util.List;

public interface UserService {

    public User login(String userCode, String password);

    // change pwd according to userId
    public boolean updatePwd(int id, String pwd);

    // total user number
    public int totalUser(String userName, int userRole);

    // get user list
    public List<User> getUserList(String userCode, int userRole);

    // add user
    public boolean addUser(User user);

    // delete user
    public boolean deleteUser(String userCode);

    // update user info
    public boolean updateUser(User user);

    // get user info
    public User getUser(String userCode);

}
