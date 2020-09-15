package com.xiaojiang.jdbc.dao.impl;

import com.xiaojiang.jdbc.dao.IUserDAO;
import com.xiaojiang.jdbc.entity.User;
import junit.framework.TestCase;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class UserDaoTest extends TestCase {

    @Test
    public void testGetUserById() {
        IUserDAO userDAO = new UserDao();
        User user = userDAO.getUserById(1);
        System.out.println(user);
    }

    @Test
    public void test_getUserByName() {
        IUserDAO userDAO = new UserDao();
        User user = userDAO.getUserByName("xiaojiang");
        System.out.println(user);
    }

    @Test
    public void test_addUser() {
        IUserDAO userDAO = new UserDao();
        List<User> users = new ArrayList<User>();
        for(int i =0;i<10;i++){
            User user = new User();
            user.setId(10+i);
            user.setName("name_"+i);
            user.setPost_date_year(2000+i);
            users.add(user);
        }

        for(User user:users){
            userDAO.addUser(user);
        }
    }

    @Test
    public void test_deleteUser() {
        IUserDAO userDAO = new UserDao();
        userDAO.deleteUser(10);
    }

    @Test
    public void test_updateUser() {
        IUserDAO userDAO = new UserDao();
        User user = new User();
        user.setId(11);
        user.setName("xiaojiang");
        user.setPost_date_year(1000);
        userDAO.updateUser(user);
    }
}