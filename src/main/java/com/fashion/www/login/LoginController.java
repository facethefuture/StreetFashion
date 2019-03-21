package com.fashion.www.login;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Component
public class LoginController {
	@RequestMapping(value="/loginpage")
	@ResponseBody
	public String login(){
		return "请登陆";
	}
}
