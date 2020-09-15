package com.xiaojiang.jdbc.dao.impl;

import com.xiaojiang.jdbc.dao.IUserDAO;
import com.xiaojiang.jdbc.entity.User;
import com.xiaojiang.jdbc.rowMapper.RowMapper;
import com.xiaojiang.jdbc.rowMapper.UserMapper;
import com.xiaojiang.jdbc.utils.JdbcUtils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class UserDao implements IUserDAO {
    public User getUserById(Integer id) {
        Connection connection = null;
        try {
            connection = JdbcUtils.getConnection();
            String qeury_sql = "select * from user where id = ?";
            Object[] params = {id};
            RowMapper<User> rowMapper = new UserMapper();
            List<User> users = JdbcUtils.executeQuery(connection,qeury_sql,params,rowMapper);
            if(null!=users&&users.size()>0){
                return users.get(0);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JdbcUtils.closeResource(connection,null,null);
        }
        return null;
    }

    public User getUserByName(String name) {
        Connection connection = null;
        try {
            connection = JdbcUtils.getConnection();
            String qeury_sql = "select * from user where name = ?";
            Object[] params = {name};
            RowMapper<User> rowMapper = new UserMapper();
            List<User> users = JdbcUtils.executeQuery(connection,qeury_sql,params,rowMapper);
            if(null!=users&&users.size()>0){
                return users.get(0);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JdbcUtils.closeResource(connection,null,null);
        }
        return null;
    }

    public void addUser(User user) {
        Connection connection = null;
        try {
            connection = JdbcUtils.getConnection();
            String insert_sql = "insert into user(id,name,post_date_year) values(?,?,?)";
            Object[] params = {user.getId(),user.getName(),user.getPost_date_year()};
            JdbcUtils.insert_or_update_delete(connection,insert_sql,params);
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            JdbcUtils.closeResource(connection,null,null);
        }
    }

    public void deleteUser(Integer id) {
        Connection connection = null;
        try {
            connection = JdbcUtils.getConnection();
            String delete_sql = "delete from user where id = ?";
            Object[] params = {id};
            JdbcUtils.insert_or_update_delete(connection,delete_sql,params);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JdbcUtils.closeResource(connection,null,null);
        }
    }

    public void updateUser(User user) {
        Connection connection = null;
        try {
            connection = JdbcUtils.getConnection();
            String update_sql = "update user set name=?,post_date_year=? where id = ?";
            Object[] params = {user.getName(),user.getPost_date_year(),user.getId()};
            JdbcUtils.insert_or_update_delete(connection,update_sql,params);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JdbcUtils.closeResource(connection,null,null);
        }
    }
}
