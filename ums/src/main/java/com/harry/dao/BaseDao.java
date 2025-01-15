package com.harry.dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

// For manipulate DB
public class BaseDao {
    private static String driver;
    private static String url;
    private static String username;
    private static String password;

    static {
        Properties prop = new Properties();
        InputStream is = BaseDao.class.getClassLoader().getResourceAsStream("db.properties");
        try {
            prop.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        driver = prop.getProperty("driver");
        url = prop.getProperty("url");
        username = prop.getProperty("username");
        password = prop.getProperty("password");
    }

    // DB connection
    public static Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

    // DB query
    public static ResultSet executeQuery(Connection conn, PreparedStatement preparedStatement, String sql, Object[] params) throws SQLException {
        preparedStatement = conn.prepareStatement(sql);
        for (int i = 0; i < params.length; i++) {
            // setObject starts from 1
            preparedStatement.setObject(i + 1, params[i]);
        }
        return preparedStatement.executeQuery();
    }

    // DB create, update, delete
    public static int executeUpdate(Connection conn, PreparedStatement preparedStatement, String sql, Object[] params) throws SQLException {
        preparedStatement = conn.prepareStatement(sql);
        for (int i = 0; i < params.length; i++) {
            // setObject starts from 1
            preparedStatement.setObject(i + 1, params[i]);
        }
        int updateRows = preparedStatement.executeUpdate();
        return updateRows;
    }

    // resource release
    public static boolean releaseResources(Connection conn, PreparedStatement ps, ResultSet rs) {
        boolean flag = true;
        try {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            flag = false;
            e.printStackTrace();
        }
        return flag;
    }

}
