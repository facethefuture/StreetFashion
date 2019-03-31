package com.fashion.www.jwt;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.fashion.www.dao.userRepository.UserRepository;
import com.fashion.www.user.User;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
public class JWTLoginFilter extends UsernamePasswordAuthenticationFilter{
//	@Autowired
//	UserRepository userRepository;
//	这里因为没有默认的构造器，所以不能用@Component   不能被容器管理的话  就不能使用自动注入了  
	UserRepository userRepository = new UserRepository();
	private AuthenticationManager authenticationManager;
	public JWTLoginFilter(AuthenticationManager authenticationManager){
		System.out.println("构造");
		this.authenticationManager = authenticationManager;
	}
	@Override
	public Authentication attemptAuthentication(HttpServletRequest req,HttpServletResponse res) throws AuthenticationException{
		System.out.println("执行了");
		System.out.println(req.getMethod());
		System.out.println(req.getParameter("username"));
		System.out.println("查询前");
		User user = userRepository.findOneUser("ssss");
//		User user = new User(1,"admin","123","管理员","USER");
		System.out.println("查询到人了");
		System.out.println(user.getUsername());
	   	List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		System.out.println("授予USER角色");
		authorities.add(new SimpleGrantedAuthority("ROLE_USER" ));
		return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword(),authorities));
	}
	@Override
	protected void successfulAuthentication(HttpServletRequest req,HttpServletResponse res,FilterChain chain,Authentication auth){
		System.out.println("调用成功方法");
		System.out.println(JSON.toJSONString(auth.getPrincipal()));
		String token = Jwts.builder()
			    .setSubject(((User)auth.getPrincipal()).getUsername())
			    .setExpiration(new Date(System.currentTimeMillis() + 60 * 60 * 24 * 1000))
			    .signWith(SignatureAlgorithm.HS512, "MyJwtSecret")
			    .compact();
			  res.addHeader("Authorization", "Bearer " + token);
	}
}
