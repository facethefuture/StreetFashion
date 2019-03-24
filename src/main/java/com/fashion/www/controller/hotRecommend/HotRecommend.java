package com.fashion.www.controller.hotRecommend;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.alibaba.fastjson.JSON;
import com.fashion.www.dao.hotRecommend.HotRecommendDao;
import com.fashion.www.goods.Goods;
import com.fashion.www.page.Page;

@Controller
public class HotRecommend {
	@Autowired
	private HotRecommendDao hotRecommend;
	@RequestMapping(value="/hotRecommend")
	@ResponseBody
	public String getHotRecommend(@RequestParam(value="page",defaultValue="1") int page,@RequestParam(value="perPage",defaultValue="10") int perPage,@RequestParam(value="description",required=false) String description){
		System.out.println(description);
		System.out.println(description == null);
		Page<Goods> pageObj = new Page<Goods>(page,perPage,hotRecommend.getTotalCount(description));
		pageObj.setDataList(hotRecommend.queryHotRecommends(page,perPage,description));
		return JSON.toJSONString(pageObj);
	}
}
