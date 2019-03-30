package com.fashion.www.jwt;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.lang.Arrays;

public class JWTAuthenticationFilter extends BasicAuthenticationFilter{
	public JWTAuthenticationFilter(AuthenticationManager authenticationManager){
		super(authenticationManager);
//		System.out.println("token验证");
	}
	@Override
	protected void doFilterInternal(HttpServletRequest request,HttpServletResponse response,FilterChain chain) throws IOException, ServletException{
		System.out.println("token验证");
		String header = request.getHeader("Authorization");
		System.out.println(header);
		  
		  if (header == null || !header.startsWith("Bearer ")) {
		   chain.doFilter(request, response);
		   return;
		  }
		  System.out.println("执行到这里");
		  UsernamePasswordAuthenticationToken authentication = getAuthentication(header);
		  
		  SecurityContextHolder.getContext().setAuthentication(authentication);
		  chain.doFilter(request, response);
		  
		 }
	 private UsernamePasswordAuthenticationToken getAuthentication(String token) {
		  Claims claims = Jwts.parser().setSigningKey("MyJwtSecret")
	                .parseClaimsJws(token.replace("Bearer ", ""))
	                .getBody();

	        //得到用户名
	        String username = claims.getSubject();

	        //得到过期时间
	        Date expiration = claims.getExpiration();

	        //判断是否过期
	        Date now = new Date();

	        if (now.getTime() > expiration.getTime()) {

	            throw new UsernameNotFoundException("该账号已过期,请重新登陆");
	        }


	        if (username != null) {
	        	List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
	    		System.out.println("role");
	    		authorities.add(new SimpleGrantedAuthority("ROLE_USER" ));
	            return new UsernamePasswordAuthenticationToken(username, null, authorities);
	        }
	        return null;
		 }
	}
