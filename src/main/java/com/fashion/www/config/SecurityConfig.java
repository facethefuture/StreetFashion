package com.fashion.www.config;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import com.fashion.www.jwt.JWTAuthenticationFilter;
import com.fashion.www.jwt.JWTLoginFilter;
import com.fashion.www.user.UserService;
@Configuration
@EnableWebSecurity
@ComponentScan(basePackages={"com.fashion.www.user","com.fashion.www.login"})
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	@Autowired 
	UserService userService;
	@Autowired
	JWTLoginFilter jwtLoginFilter;
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception{
		auth.userDetailsService(userService);
		
	}
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests()
//		 允许以下请求
        .antMatchers("/login").permitAll().antMatchers("/hellow").permitAll()
//         所有请求需要身份认证
        .anyRequest().authenticated()
//      验证登陆
        .and()
        .addFilter(new JWTLoginFilter(authenticationManager()))
//        验证token
        .addFilter(new JWTAuthenticationFilter(authenticationManager()));
//		.and().formLogin().loginPage("/login").successHandler(
//				new MyAuthenticationSuccessHandler()
//		).failureHandler(new MyAuthenticationFailureHandler()).loginProcessingUrl("/login")
//		.and().logout().permitAll().and().csrf().disable();
//		http.authorizeRequests().antMatchers("/user/**").hasRole("USER")
//		.and().formLogin().loginPage("/login").successHandler(
//				new MyAuthenticationSuccessHandler()
//		).failureHandler(new MyAuthenticationFailureHandler()).loginProcessingUrl("/login")
//		.and().logout().permitAll().and().csrf().disable();
	}
	
}
