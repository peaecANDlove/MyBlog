package com.peace.myblog.config;

import com.peace.myblog.service.Impl.CustomUserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author YR#
 * @create 2020-08-12 19:31
 */

@EnableWebSecurity
public class MySecurityConfig extends WebSecurityConfigurerAdapter {




    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .antMatchers("/index", "/login", "/admin/login","/user/login", "/static/**").permitAll()

                .and()
                .formLogin().loginPage("/user/login")
                .defaultSuccessUrl("/")


                .and()
                .logout().logoutSuccessUrl("/")

                .and()
                .csrf().disable();

        http.headers().frameOptions().sameOrigin();





    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.userDetailsService(customUserDetailsService())
             .passwordEncoder(encoder());
    }


    @Bean
    public BCryptPasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CustomUserDetailsServiceImpl customUserDetailsService() {

        return new CustomUserDetailsServiceImpl();
    }





}
