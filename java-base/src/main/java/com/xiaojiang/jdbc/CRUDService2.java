package com.xiaojiang.jdbc;

import com.xiaojiang.jdbc.model.User;
import com.xiaojiang.jdbc.utils.JdbcUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CRUDService2 {


    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        Connection conn = JdbcUtils.getConnection();
        //查询的sql语句
        String query_sql = "select * from user";
        //3.查询数据，获取ResultSet对象
        PreparedStatement  preparedStatement = conn.prepareStatement(query_sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        //处理结果数据
        List<User> users = new ArrayList<User>();
        if(resultSet!=null){
            User user = null;
            while(resultSet.next()){
                user = new User();
                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                user.setPost_date_year(resultSet.getInt("post_date_year"));
                users.add(user);
            }
        }

        for(User user:users){
            System.out.println(user.toString());
        }

        //4. 关闭各种DB操作对象
        JdbcUtils.closeResource(conn,preparedStatement,resultSet);

    }

}
