package com.xiaojiang.jdbc.dao;

import com.xiaojiang.jdbc.entity.User;

public interface IUserDAO {

    //根据id查询user对象
    public User getUserById(Integer id);

    //根据name查询user对象
    public User getUserByName(String name);

    //添加user对象
    public void addUser(User user);

    //删除user对象
    public void deleteUser(Integer id);

    //更改user对象
    public void updateUser(User user);

}
