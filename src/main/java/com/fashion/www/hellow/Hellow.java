package com.fashion.www.hellow;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class Hellow {
	@RequestMapping(value="/hellow")
	@ResponseBody
	public String testHellow(){
		return "hellow";
	}
	@RequestMapping(value="/user/test")
	@ResponseBody
	public String testSecurity(){
		return "Security";
	}
}
