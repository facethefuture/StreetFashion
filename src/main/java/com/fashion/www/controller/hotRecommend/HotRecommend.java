package com.fashion.www.controller.hotRecommend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.fashion.www.dao.hotRecommend.HotRecommendDao;

@Controller
public class HotRecommend {
	@Autowired
	private HotRecommendDao hotRecommend;
	@RequestMapping(value="/hotRecommend")
	@ResponseBody
	public String getHotRecommend(){
		return JSON.toJSONString(hotRecommend.queryHotRecommends());
	}
}
