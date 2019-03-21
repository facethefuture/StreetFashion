package com.fashion.www.user;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class User implements UserDetails{
	private int id;
	private String username;
	private String password;
	private String nickname;
	private String role;
	public User(){}
	public User(int id,String username,String password,String nickname,String role){
		this.id = id;
		this.username = username;
		this.password = password;
		this.nickname = nickname;
		this.role = role;
	}
	public List<GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		System.out.println("role" + this.role);
		authorities.add(new SimpleGrantedAuthority("ROLE_" + this.role));
		return authorities;
	}
	public int getId(){
		return this.id;
	}
	public String getPassword() {
		// TODO Auto-generated method stub
		return this.password;
	}
	public void setUsername(String name){
		this.nickname = name;
	}

	public String getUsername() {
		// TODO Auto-generated method stub
		return this.username;
	}
	public String getNickname(){
		return this.nickname;
	}
	public String getRole(){
		return this.role;
	}
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
