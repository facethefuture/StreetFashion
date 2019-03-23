package com.fashion.www.controller.hotRecommend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.fashion.www.dao.hotRecommend.PhotographyModuleDao;
import com.fashion.www.goods.PhotographyModule;
import com.fashion.www.page.Page;

@Controller
public class PhotographyModuleController {
	@Autowired
	private PhotographyModuleDao pmd;
	@RequestMapping(value="/queryPhotographyModule")
	@ResponseBody
	public String queryPhotoGraphyModule(){
		Page<PhotographyModule> page = new Page<PhotographyModule>();
		page.setDataList(pmd.queryPhotographyModule());
		return JSON.toJSONString(page);
	}
}
