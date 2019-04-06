package com.fashion.www.controller.upload;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.fashion.www.dao.hotRecommend.HotRecommendDao;
import com.fashion.www.dao.photography.PhotographyDao;

@Controller
public class UploadFile {
	@Autowired
	private HotRecommendDao hotRecommend;
	@Autowired
	private PhotographyDao photographyDao;
	@RequestMapping(value="/upload")
	@ResponseBody
	public String upload(MultipartHttpServletRequest request,@RequestParam(value="description") String description,@RequestParam(value="type") int type){
		System.out.println("上传");
		System.out.println(description);
		String path = request.getSession().getServletContext().getRealPath("/assets/image/hotRecommend/");
		System.out.println(path);
		System.out.println(type);
		System.out.println(type == 1);
		try {
			MultipartFile file = request.getFile("image");
			File imageFile = new File(path + '/' + file.getOriginalFilename());
			file.transferTo(imageFile);
			String coverImagePath = "/assets/image/hotRecommend/" + file.getOriginalFilename();
			if (type == 1) {
				hotRecommend.createHotRecommend("街拍", coverImagePath, description);
			} else {
				photographyDao.createPhotography("摄影", coverImagePath, description);
			}
			
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
}