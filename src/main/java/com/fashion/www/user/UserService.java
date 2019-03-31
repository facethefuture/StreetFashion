package com.fashion.www.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.fashion.www.dao.userRepository.UserRepository;
@Component
public class UserService implements UserDetailsService{
	@Autowired
	private UserRepository userDao;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		System.out.println("用户名"+username);
		System.out.println(username);
		User user = userDao.findOneUser(username);
		System.out.println(JSON.toJSON(user));
		if (user != null){
			return user;
		}
		return new User();
	}

}
