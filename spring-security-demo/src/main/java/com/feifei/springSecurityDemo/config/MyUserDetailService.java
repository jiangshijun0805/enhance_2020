package com.feifei.springSecurityDemo.config;

import com.feifei.springSecurityDemo.entities.User;
import com.feifei.springSecurityDemo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailService implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

        //从数据库中获取用户
        User user = userMapper.findByUserName(userName);
        //用户不存在，抛出异常
        if(user==null){
            throw new UsernameNotFoundException("用户不存在");
        }

        user.setAnthorities(AuthorityUtils.commaSeparatedStringToAuthorityList(user.getRoles()));

        return user;
    }
}
