package com.fashion.www.controller.hotRecommend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
	public String getHotRecommend(@RequestParam(value="page",defaultValue="1") int page,@RequestParam(value="perPage",defaultValue="10") int perPage){
		Page<Goods> pageObj = new Page<Goods>(page,perPage,hotRecommend.getTotalCount());
		pageObj.setDataList(hotRecommend.queryHotRecommends(page,perPage));
		return JSON.toJSONString(pageObj);
	}
}
