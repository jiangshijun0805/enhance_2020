package com.feifei.springSecurityDemo.mapper;

import com.feifei.springSecurityDemo.entities.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

@Component
public interface UserMapper {

    @Select("select * from users where userName=#{userName}")
    User findByUserName(@Param("userName") String userName);
}
