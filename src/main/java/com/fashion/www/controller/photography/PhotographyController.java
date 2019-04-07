package com.fashion.www.controller.photography;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
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
	public String getPhotography(@RequestParam(value="page",defaultValue="1") int page,@RequestParam(value="perPage",defaultValue="10") int perPage,@RequestParam(value="description",required=false) String description,@RequestParam(value="enable",defaultValue="1") int enable,@RequestParam(value="startTime", defaultValue="1") int startTime,@RequestParam(value="endTime",defaultValue="1999999999") int endTime) throws UnsupportedEncodingException{
		System.out.println(URLDecoder.decode(description == null ? "" : description, "utf-8"));
		System.out.println(description == null);
		Page<Goods> pageObj = new Page<Goods>(page,perPage,photographyDao.getTotalCount(description == null ? description : URLDecoder.decode( description, "utf-8"),enable,startTime,endTime));
		pageObj.setDataList(photographyDao.queryPhotography(page,perPage,description == null ? description : URLDecoder.decode( description, "utf-8"),enable,startTime,endTime));
		return JSON.toJSONString(pageObj);
	}
	@RequestMapping(value="/photography/{id}")
	@ResponseBody
	public String findOnePhotography(@PathVariable("id") int id){
		Goods good = photographyDao.findPhotographyById(id);
		return JSON.toJSONString(good);
	}
	@RequestMapping(value = "/admin/updatePhotography")
	@ResponseBody
	public String updatePhotographyById(@RequestParam(value="id") int id,@RequestParam(value="enable") int enable){
		photographyDao.updatePhotographyById(id, enable);
		return "success";
	}
}
