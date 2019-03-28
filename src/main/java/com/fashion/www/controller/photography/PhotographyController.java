package com.fashion.www.controller.photography;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.alibaba.fastjson.JSON;
import com.fashion.www.dao.photography.PhotographyDao;
import com.fashion.www.goods.Goods;
import com.fashion.www.page.Page;

@Controller
public class PhotographyController {
	@Autowired
	private PhotographyDao photographyDao;
	@RequestMapping(value="/photography")
	@ResponseBody
	public String getPhotography(@RequestParam(value="page",defaultValue="1") int page,@RequestParam(value="perPage",defaultValue="10") int perPage,@RequestParam(value="description",required=false) String description){
		System.out.println(description);
		System.out.println(description == null);
		Page<Goods> pageObj = new Page<Goods>(page,perPage,photographyDao.getTotalCount(description));
		pageObj.setDataList(photographyDao.queryPhotographies(page,perPage,description));
		return JSON.toJSONString(pageObj);
	}
	@RequestMapping(value="/photography/{id}")
	@ResponseBody
	public String findOnePhotography(@PathVariable("id") int id){
		Goods good = photographyDao.findPhotographyById(id);
		return JSON.toJSONString(good);
	}
}
