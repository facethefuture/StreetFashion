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
	public String getHotRecommend(@RequestParam(value="page",defaultValue="1") int page,@RequestParam(value="perPage",defaultValue="10") int perPage){
		Page<Goods> pageObj = new Page<Goods>(page,perPage,hotRecommend.getTotalCount());
		pageObj.setDataList(hotRecommend.queryHotRecommends(page,perPage));
		return JSON.toJSONString(pageObj);
	}
	@RequestMapping(value="/upload")
	@ResponseBody
	public String upload(MultipartHttpServletRequest request,@RequestParam(value="description") String description){
		System.out.println("上传");
		System.out.println(description);
		String path = request.getSession().getServletContext().getRealPath("/assets/image/hotRecommend/");
		System.out.println(path);
		try {
			MultipartFile file = request.getFile("image");
			File imageFile = new File(path + '/' + file.getOriginalFilename());
			file.transferTo(imageFile);
			String coverImagePath = "/assets/image/hotRecommend/" + file.getOriginalFilename();
			hotRecommend.createHotRecommend("上传测试", coverImagePath, description);
			return "success";
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "failed";
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "failed";
		}
		
	}
	
	@RequestMapping(value="/testUpload")
	@ResponseBody
	public String testUpload(@RequestParam("name") String name){
		System.out.println(name);
		return "test";
	}
}
