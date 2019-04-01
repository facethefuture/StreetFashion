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
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.fashion.www.dao.userRepository.UserRepository;
import com.fashion.www.user.User;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JWTLoginFilter extends UsernamePasswordAuthenticationFilter{
	@Autowired
	UserRepository userRepository;
//	这里因为没有默认的构造器，所以不能用@Component   不能被容器管理的话  就不能使用自动注入了
//	UserRepository userRepository = new UserRepository();
	private AuthenticationManager authenticationManager;
	public JWTLoginFilter(AuthenticationManager authenticationManager){
		System.out.println("构造");
		setAuthenticationManager(authenticationManager);
	}
	@Override
	public Authentication attemptAuthentication(HttpServletRequest req,HttpServletResponse res) throws AuthenticationException{
		System.out.println("执行了");
//		System.out.println(req.getMethod());
		System.out.println(req.getParameter("username"));
//		System.out.println(req.getAttribute(getUsernameParameter()));
//		System.out.println(req.getContentType());
//		System.out.println(req.getParameter("password"));
		System.out.println("查询前");
		String username = req.getParameter("username");
        String password = req.getParameter("password");
		User user = userRepository.findOneUser(username);
//		User user = new User(1,"admin","123","管理员","USER");
		System.out.println("查询到人了");
		System.out.println(JSON.toJSONString(user));
	
		if (user == null) {
			 throw new UsernameNotFoundException("账号或密码错误");
		}
        if (username == null) {
            username = "";
        }

        if (password == null) {
            password = "";
        }

        username = username.trim();


        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword(), user.getAuthorities());

        //authenticate()接受一个token参数,返回一个完全经过身份验证的对象，包括证书.
        // 这里并没有对用户名密码进行验证,而是使用 AuthenticationProvider 提供的 authenticate 方法返回一个完全经过身份验证的对象，包括证书.
//        Authentication authenticate = authenticationManager.authenticate(authenticationToken);

//UsernamePasswordAuthenticationToken 是 Authentication 的实现类
        return authenticationToken;

	}
	@Override
	protected void successfulAuthentication(HttpServletRequest req,HttpServletResponse res,FilterChain chain,Authentication auth){
		System.out.println("调用成功方法");
		System.out.println(JSON.toJSONString(auth.getPrincipal()));
		String token = Jwts.builder()
				.setSubject(auth.getName())
                //有效期两小时
                .setExpiration(new Date(System.currentTimeMillis() + 60 * 60 * 2 * 1000))
                //采用什么算法是可以自己选择的，不一定非要采用HS512
                .signWith(SignatureAlgorithm.HS512, "MyJwtSecret")
                .compact();
			  res.addHeader("Authorization", "Bearer " + token);
	}
}
