package com.fashion.www.controller.hotRecommend;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
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
	public String getHotRecommend(@RequestParam(value="page",defaultValue="1") int page,@RequestParam(value="perPage",defaultValue="10") int perPage,@RequestParam(value="description",required=false) String description,@RequestParam(value="enable",defaultValue="1") int enable,@RequestParam(value="startTime") int startTime,@RequestParam(value="endTime") int endTime) throws UnsupportedEncodingException{
		System.out.println(URLDecoder.decode(description == null ? "" : description, "utf-8"));
		System.out.println(description == null);
		Page<Goods> pageObj = new Page<Goods>(page,perPage,hotRecommend.getTotalCount(description == null ? description : URLDecoder.decode( description, "utf-8"),enable,startTime,endTime));
		pageObj.setDataList(hotRecommend.queryHotRecommends(page,perPage,description == null ? description : URLDecoder.decode( description, "utf-8"),enable,startTime,endTime));
		return JSON.toJSONString(pageObj);
	}
	@RequestMapping(value="/streetSnap/{id}")
	@ResponseBody
	public String findOneStreetSnap(@PathVariable("id") int id){
		Goods good = hotRecommend.findStreetSnapById(id);
		return JSON.toJSONString(good);
	}
	@RequestMapping(value = "/admin/updateHotRecommend")
	@ResponseBody
	public String updateHotRecommendById(@RequestParam(value="id") int id,@RequestParam(value="enable") int enable){
		hotRecommend.updateHotRecommend(id, enable);
		return "success";
	}
}
