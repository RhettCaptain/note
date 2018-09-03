//package com.lannister.sparkhire;
//
//import org.springframework.boot.autoconfigure.security.SpringBootWebSecurityConfiguration;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//
///**
// * Created by yang_fa-pc on 2018/8/29.
// */
//@Configuration
//@EnableWebSecurity
//public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//    @Override
//    protected  void configure(HttpSecurity http) throws Exception {
//        http
//             .authorizeRequests()
//                .antMatchers("/","/home").permitAll()
//                .anyRequest().authenticated()
//                .and()
//             .formLogin()
//                .loginPage("/login")
//                .permitAll()
//                .and()
//             .logout()
//                .permitAll();
//    }
//}
