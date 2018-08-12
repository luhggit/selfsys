package com.example.springboot.service;

import com.example.springboot.repository.SysUserRepository;
import com.example.springboot.entity.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class CustomUserService implements UserDetailsService{

    @Autowired
    SysUserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        SysUser user = userRepository.findByUsername(username);

        if (user == null){
            throw new UsernameNotFoundException("用户名不存在");
        }

        System.out.println("CustomUserService username:" + user.getUsername() + " password:" +  user.getPassword());

        return user;
    }
}
