package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import dao.query.agreeQueryParams;
import json.jsonResult;
import pojo.agree;
import servicesDao.agreeServiceDao;

@Controller
@RequestMapping("/AgreeServlet")
public class agreeController extends controllerTemplate<agree, agreeServiceDao, agreeQueryParams>{

	
	protected jsonResult query(agreeQueryParams im) throws Exception {
		
		return serviceDao.getPagesResultBasic(im);
	}
	protected void androidInsertTemplateBefor(agreeQueryParams im) throws Exception{
         if(im==null||im.getUser()==null||im.getUser().getType()==null||im.getUser().getId()==0||im.getUser().getType()<=0||im.getUser().getType()>=3){
        	 throw new Exception("没有点赞权限，请注册登录");
         }
	}
	protected jsonResult androidUpdateTemplate(agreeQueryParams im) throws Exception {
		return new jsonResult(serviceDao.delete(im.getObj()),"删除");
	}
}
