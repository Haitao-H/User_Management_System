package com.harry.dao.user;

import com.harry.dao.BaseDao;
import com.harry.pojo.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {

    // get login user info
    public User getLoginUser(Connection connection, String userCode) throws SQLException {

        User user = null;

        if (connection != null) {
            String sql = "select * from user where userCode = ?";
            Object[] params = {userCode};

            ResultSet resultSet = BaseDao.executeQuery(connection, null, sql, params);
            if (resultSet.next()) {
                user = new User();
                user.setId(resultSet.getInt("id"));
                user.setUserCode(resultSet.getString("userCode"));
                user.setFirstName(resultSet.getString("firstName"));
                user.setLastName(resultSet.getString("lastName"));
                user.setUserPassword(resultSet.getString("userPassword"));
                user.setGender(resultSet.getInt("gender"));
                user.setBirthday(resultSet.getDate("birthday"));
                user.setBirthday(resultSet.getDate("birthday"));
                user.setPhone(resultSet.getString("phone"));
                user.setAddress(resultSet.getString("address"));
                user.setUserRole(resultSet.getInt("userRole"));
                user.setCreationBy(resultSet.getInt("creationBy"));
                user.setCreationDate(resultSet.getDate("creationDate"));
                user.setModifyBy(resultSet.getInt("modifyBy"));
                user.setModifyDate(resultSet.getDate("modifyDate"));
            }
            BaseDao.releaseResources(null, null, resultSet);
        }
        return user;
    }

    // change current user password
    public int updatePwd(Connection connection, int id, String password) throws SQLException {
        PreparedStatement preparedStatement = null;
        int updatedRow = 0;

        if (connection != null) {
            String sql = "update user set userPassword = ? where id = ?";
            Object[] params = {password, id};
            updatedRow = BaseDao.executeUpdate(connection, preparedStatement, sql, params);
            BaseDao.releaseResources(null, preparedStatement, null);
        }
        return updatedRow;
    }

    // get user number with userCode and userRole
    public int totalUser(Connection connection, String userCode, int userRole) throws SQLException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int count = 0;

        if (connection != null) {
            StringBuffer sql = new StringBuffer();
            sql.append("select count(*) from user, role where user.userRole = role.id");
            // save the params for executeQuery
            ArrayList<Object> list = new ArrayList<>();
            if (userCode != null) {
                sql.append(" and user.userCode like ?");
                list.add("%" + userCode + "%");
            }
            if (userRole > 0) {
                sql.append(" and user.userRole = ?");
                list.add(userRole);
            }
            Object[] params = list.toArray();

            resultSet = BaseDao.executeQuery(connection, preparedStatement, sql.toString(), params);
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
            BaseDao.releaseResources(null, preparedStatement, null);
        }
        return count;
    }

    // get user list
    public List<User> getUserList(Connection connection, String userName, int userRole) throws SQLException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<User> users = new ArrayList<>();
        if (connection != null) {
            StringBuffer sql = new StringBuffer();
            sql.append("select * from user, role where user.userRole = role.id");
            List<Object> list = new ArrayList<>();
            if (userName != null) {
                sql.append(" and user.userCode like ?");
                list.add("%" + userName + "%");
            }
            if (userRole > 0) {
                sql.append(" and user.userRole = ?");
                list.add(userRole);
            }
            sql.append(" order by user.id");

            Object[] params = list.toArray();

            resultSet = BaseDao.executeQuery(connection, preparedStatement, sql.toString(), params);
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setUserCode(resultSet.getString("userCode"));
                user.setFirstName(resultSet.getString("firstName"));
                user.setLastName(resultSet.getString("lastName"));
                user.setGender(resultSet.getInt("gender"));
                user.setBirthday(resultSet.getDate("birthday"));
                user.setPhone(resultSet.getString("phone"));
                user.setUserRole(resultSet.getInt("userRole"));
                user.setUserRoleName(resultSet.getString("roleName"));
                users.add(user);
            }
            BaseDao.releaseResources(null, preparedStatement, null);
        }
        return users;
    }

    // add new user
    public int addUser(Connection connection, User user) throws SQLException {
        PreparedStatement preparedStatement = null;
        int updatedRow = 0;
        if (connection != null) {
            String sql = "insert into "
                    + "user(userCode, firstName, lastName, userPassword, gender, birthday, phone, address, userRole, creationBy, creationDate) "
                    + "values (?,?,?,?,?,?,?,?,?,?,?)";
            Object[] params = {user.getUserCode(), user.getFirstName(), user.getLastName(), user.getUserPassword(),
                    user.getGender(), user.getBirthday(), user.getPhone(), user.getAddress(),
                    user.getUserRole(), user.getCreationBy(), user.getCreationDate()};
            updatedRow = BaseDao.executeUpdate(connection, preparedStatement, sql, params);
            BaseDao.releaseResources(null, preparedStatement, null);
        }
        return updatedRow;
    }

    // delete user by userCode
    public int deleteUser(Connection connection, String userCode) throws SQLException {
        PreparedStatement preparedStatement = null;
        int updatedRow = 0;
        if (connection != null) {
            String sql = "delete from user where userCode = ?";
            Object[] params = {userCode};
            updatedRow = BaseDao.executeUpdate(connection, preparedStatement, sql, params);
            BaseDao.releaseResources(null, preparedStatement, null);
        }
        return updatedRow;
    }

    // update user info
    public int updateUser(Connection conn, User user) throws SQLException {
        PreparedStatement preparedStatement = null;
        int updatedRow = 0;
        if (conn != null) {
            String sql = "update user set gender=?, birthday=?, phone=?, address=?, userRole=?, modifyBy=?, modifyDate=? where userCode=?";
            Object[] params = {user.getGender(), user.getBirthday(), user.getPhone(), user.getAddress(), user.getUserRole(),
                    user.getModifyBy(), user.getModifyDate(), user.getUserCode()};
            updatedRow = BaseDao.executeUpdate(conn, preparedStatement, sql, params);
            BaseDao.releaseResources(null, preparedStatement, null);
        }
        return updatedRow;
    }

}
