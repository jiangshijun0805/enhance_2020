package com.xiaojiang.jdbc.utils;

import com.xiaojiang.jdbc.rowMapper.RowMapper;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * JdbcUtils 操作数据库的工具方法
 */
public final class JdbcUtils {

    //设置数据库连接的的属性字段
    private static final String DRIVER;
    private static final String URL;
    private static final String USERNAME;
    private static final String PASSWORD;

    //私有化构造函数
    private JdbcUtils(){

    }

    //类加载时初始化变量
    static{
        //加载数据库DB的配置信息
        InputStream is = JdbcUtils.class.getClassLoader().getResourceAsStream("jdbc.properties");
        Properties properties = new Properties();
        try {
            properties.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        DRIVER = properties.getProperty("jdbc.driver");
        URL = properties.getProperty("jdbc.url");
        USERNAME = properties.getProperty("jdbc.userName");
        PASSWORD = properties.getProperty("jdbc.password");

        //加载数据库Driver驱动
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取connection ;  每次执行会新创建一个connection对象
     * @return
     * @throws SQLException
     * 说明：此次并没有限制数据库连接的个数，当每个数据库连接操作完成后，需要主动关闭连接资源
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL,USERNAME,PASSWORD);
    }

    public static void closeResource(Connection conn, Statement st, ResultSet rs){
        //注意顺序
        closeResultSet(rs);
        closeStatement(st);
        closeConnection(conn);
    }

    /**
     * 释放连接connection
     * @param conn
     */
    public static void closeConnection(Connection conn){
        if(conn!=null){
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        //等待垃圾回收
        conn = null;
    }

    /**
     * 释放语句执行者Statement
     * @param statement
     */
    public static void closeStatement(Statement statement){
        if(statement!=null){
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        //等待垃圾回收
        statement = null;
    }

    /**
     * 释放结果集 ResultSet
     * @param resultSet
     */
    public static void closeResultSet(ResultSet resultSet){
        if(resultSet!=null){
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        //等待垃圾回收
        resultSet = null;
    }

    /**
     * 单条数据的插入操作   和  更新操作  和 删除操作
     * @param connection
     * @param insert_sql
     * @param params
     * @return
     */
    public static int insert_or_update_delete(Connection connection,String insert_sql,Object[] params){
        PreparedStatement preparedStatement = null;
        int count = -1;
        try {
            preparedStatement = connection.prepareStatement(insert_sql);
            if(null!=params&&params.length>0){
                for(int i=0;i<params.length;i++){
                    preparedStatement.setObject(i+1,params[i]);
                }
            }
            count = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JdbcUtils.closeResource(connection,preparedStatement,null);
        }
        return count;
    }

    public static <T> List<T> executeQuery(Connection connection, String query_sql, Object[] params, RowMapper<T> rowMapper){
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<T> list = new ArrayList<T>();

        try {
            preparedStatement = connection.prepareStatement(query_sql);
            if(null!=params&&params.length>0){
                for(int i=0;i<params.length;i++){
                    preparedStatement.setObject(i+1,params[i]);
                }
            }
            resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                T t = rowMapper.getEntity(resultSet);
                list.add(t);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    /*public static void main(String[] args) throws IOException {
        InputStream is = JdbcUtils.class.getClassLoader().getResourceAsStream("jdbc.properties");
        Properties properties = new Properties();
        properties.load(is);
        String driver = properties.getProperty("jdbc.driver");
        String url = properties.getProperty("jdbc.url");
        String userName = properties.getProperty("jdbc.userName");
        String password = properties.getProperty("jdbc.password");
        System.out.println("dirver = "+driver);
        System.out.println("url = "+url);
        System.out.println("userName ="+userName);
        System.out.println("password ="+password);
    }*/

    /*public static void main(String[] args) throws SQLException {
        Connection connection = JdbcUtils.getConnection();
        String insert_sql = "insert into user values(?,?,?)";
        Object[] params = {5,"huyuling","1987"};
        int count = JdbcUtils.insert(connection,insert_sql,params);
        System.out.println("count = "+count);
    }*/

    /*public static void main(String[] args) throws SQLException {
        Connection connection = JdbcUtils.getConnection();
        String insert_sql = "update user set name=? where id =?";
        Object[] params = {"小胡",5};
        int count = JdbcUtils.insert_or_update_delete(connection,insert_sql,params);
        System.out.println("count = "+count);
    }*/

    public static void main(String[] args) throws SQLException {
        Connection connection = JdbcUtils.getConnection();
        String insert_sql = "delete from user where id =?";
        Object[] params = {4};
        int count = JdbcUtils.insert_or_update_delete(connection,insert_sql,params);
        System.out.println("count = "+count);
    }
}
