package com.harry.service.role;

import com.harry.dao.BaseDao;
import com.harry.dao.role.RoleDao;
import com.harry.dao.role.RoleDaoImpl;
import com.harry.pojo.Role;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class RoleServiceImpl implements RoleService {

    private RoleDao roleDao;

    public RoleServiceImpl() {
        roleDao = new RoleDaoImpl();
    }

    // get <list> for all role
    public List<Role> getRolesList() {
        Connection connection = null;
        List<Role> roles = null;
        try {
            connection = BaseDao.getConnection();
            roles = roleDao.getRoleList(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            BaseDao.releaseResources(connection, null, null);
        }
        return roles;
    }

}
