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
import io.jsonwebtoken.ExpiredJwtException;
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
		System.out.println("解析Authentication");
		 Claims claims = null;
		 //解析Authentication  如果过期 就返回null,记得  这里要用try catch去捕获异常  而不是简单的抛出，不然会报500错误  而不是403
		try{
			claims = Jwts.parser().setSigningKey("MyJwtSecret")
					.parseClaimsJws(token.replace("Bearer ", ""))
					.getBody();
		}catch(ExpiredJwtException e){
			e.printStackTrace();
			return null;
		}
	        //得到用户名
	        String username = claims.getSubject();

	        //得到过期时间
	        Date expiration = claims.getExpiration();

	        //判断是否过期
	        Date now = new Date();

	        if (now.getTime() > expiration.getTime()) {
//                return null;
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
