package com.harry.dao.role;

import com.harry.dao.BaseDao;
import com.harry.pojo.Role;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoleDaoImpl implements RoleDao {

    // get <list> for all role
    public List<Role> getRoleList(Connection connection) throws SQLException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Role> roles = new ArrayList<>();
        if (connection != null) {
            StringBuffer sql = new StringBuffer();
            sql.append("select * from role");
            Object[] params = {};
            resultSet = BaseDao.executeQuery(connection, preparedStatement, sql.toString(), params);
            while (resultSet.next()) {
                Role role = new Role();
                role.setId(resultSet.getInt("id"));
                role.setRoleName(resultSet.getString("roleName"));
                roles.add(role);
            }
        }
        BaseDao.releaseResources(null, preparedStatement, null);
        return roles;
    }

}

