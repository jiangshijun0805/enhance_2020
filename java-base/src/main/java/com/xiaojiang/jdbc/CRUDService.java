package com.xiaojiang.jdbc;

import com.xiaojiang.jdbc.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CRUDService {


    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        //DB url
        String url = "jdbc:mysql://localhost:3306/demo?characterEncoding=utf-8&useSSL=false";
        //DB user
        String userName = "root";
        //DB password
        String password = "12345678";
        //1.加载驱动
        Class.forName("com.mysql.jdbc.Driver");
        //2.获取connection对象
        Connection conn = DriverManager.getConnection(url,userName,password);
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
        resultSet.close();
        preparedStatement.close();
        conn.close();

    }

}
