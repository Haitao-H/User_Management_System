package com.harry.dao.role;

import com.harry.pojo.Role;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface RoleDao {

    // get <list> for all role
    public List<Role> getRoleList(Connection connection) throws SQLException;
}
