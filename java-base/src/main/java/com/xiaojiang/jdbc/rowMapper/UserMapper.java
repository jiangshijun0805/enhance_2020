package com.xiaojiang.jdbc.rowMapper;

import com.xiaojiang.jdbc.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements RowMapper<User> {
    public User getEntity(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setId(resultSet.getInt("id"));
        user.setName(resultSet.getString("name"));
        user.setPost_date_year(resultSet.getInt("post_date_year"));
        return user;
    }
}
