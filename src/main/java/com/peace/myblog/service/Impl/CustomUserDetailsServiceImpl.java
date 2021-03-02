package com.peace.myblog.service.Impl;

import com.peace.myblog.daoObject.User;
import com.peace.myblog.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author YR#
 * @create 2020-08-12 19:42
 */

@Service
@Slf4j
public class CustomUserDetailsServiceImpl  implements UserDetailsService {

    @Autowired
    private UserService userService;

    private Collection<GrantedAuthority> getAuthorities(User user){
        List<GrantedAuthority> authList = new ArrayList<GrantedAuthority>();
        authList.add(new SimpleGrantedAuthority("ROLE_"+user.getRole()));
        return authList;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {



        User user = userService.getUserByAccountNumber(username);

        if(user == null) {
            throw new RuntimeException("用户不存在！");
        }

        Collection<GrantedAuthority> authorities = getAuthorities(user);
        UserDetails userDetails = new org.springframework.security.core.userdetails.User(username, user.getPassword(), authorities);

        return userDetails;
    }
}
