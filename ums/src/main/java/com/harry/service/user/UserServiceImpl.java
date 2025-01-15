package com.harry.service.user;

import com.harry.dao.BaseDao;
import com.harry.dao.user.UserDao;
import com.harry.dao.user.UserDaoImpl;
import com.harry.pojo.User;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class UserServiceImpl implements UserService {

    private UserDao userDao;

    public UserServiceImpl() {
        userDao = new UserDaoImpl();
    }

    public User login(String userCode, String password) {
        Connection conn = null;
        User user = null;

        try {
            conn = BaseDao.getConnection();
            user = userDao.getLoginUser(conn, userCode);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            BaseDao.releaseResources(conn, null, null);
        }
        return user;
    }

    public boolean updatePwd(int id, String pwd) {
        Connection conn = null;
        conn = BaseDao.getConnection();
        boolean flag = false;
        try {
            if (userDao.updatePwd(conn, id, pwd) > 0) {
                flag = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            BaseDao.releaseResources(conn, null, null);
        }
        return flag;
    }

    public int totalUser(String userName, int userRole) {
        Connection conn = null;
        int total = 0;
        try {
            conn = BaseDao.getConnection();
            total = userDao.totalUser(conn, userName, userRole);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            BaseDao.releaseResources(conn, null, null);
        }
        return total;
    }

    public List<User> getUserList(String userCode, int userRole) {
        Connection conn = null;
        List<User> users = null;
        try {
            conn = BaseDao.getConnection();
            users = userDao.getUserList(conn, userCode, userRole);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            BaseDao.releaseResources(conn, null, null);
        }
        return users;
    }

    public boolean addUser(User user) {
        boolean added = false;
        Connection conn = null;
        try {
            conn = BaseDao.getConnection();
            conn.setAutoCommit(false);
            if (userDao.addUser(conn, user) > 0) {
                added = true;
                conn.commit();
                System.out.println("user added successfully");
            } else {
                System.out.println("user added fail");
                conn.rollback();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            BaseDao.releaseResources(conn, null, null);
        }
        return added;
    }

    public boolean deleteUser(String userCode) {
        boolean deleted = false;
        Connection conn = null;
        try {
            conn = BaseDao.getConnection();
            conn.setAutoCommit(false);
            if (userDao.deleteUser(conn, userCode) > 0) {
                deleted = true;
                conn.commit();
                System.out.println(userCode + " deleted successfully");
            } else {
                System.out.println(userCode + " deleted fail");
                conn.rollback();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            BaseDao.releaseResources(conn, null, null);
        }
        return deleted;
    }

    public boolean updateUser(User user) {
        boolean updated = false;
        Connection conn = null;
        try {
            conn = BaseDao.getConnection();
            conn.setAutoCommit(false);
            if (userDao.updateUser(conn, user) > 0) {
                updated = true;
                conn.commit();
                System.out.println("user updated successfully");
            } else {
                System.out.println("user updated fail");
                conn.rollback();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            BaseDao.releaseResources(conn, null, null);
        }
        return updated;
    }

    public User getUser(String userCode) {
        return this.login(userCode, "");
    }


    @Test
    public void test() {
        UserServiceImpl tester = new UserServiceImpl();

//        User user = new User();
//        user.setUserCode("test01");
//        user.setFirstName("test");
//        user.setLastName("01");
//        user.setUserPassword("0000000");
//        user.setGender(1);
//        user.setBirthday(new Date());
//        user.setPhone("282845921");
//        user.setAddress("xxxx");
//        user.setUserRole(3);
//        user.setCreationBy(1);
//        user.setCreationDate(new Date());
//        System.out.println(tester.addUser(user));

//        tester.deleteUser("test03");


//        tester.updateUser(14, (new Object[]{2, new Date(), "9295131476", "xxx st, NY, NY", 1, 1, new Date(), 14}));

    }


}
