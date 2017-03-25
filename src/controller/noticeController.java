package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import controller.userLogin.currentUser;
import dao.query.newsQueryParams;
import dao.query.noticeQueryParams;
import json.jsonResult;
import pojo.interaction;
import pojo.notice;
import servicesDao.noticeServicDao;
@Controller
@RequestMapping("/NoticeServlet")
public class noticeController extends controllerTemplate<notice, noticeServicDao, noticeQueryParams>{

	
	protected jsonResult query(noticeQueryParams im) throws Exception {
		
		return serviceDao.getPagesResultBasic(im);
	}
	protected void androidInsertTemplateBefor(noticeQueryParams im){
		   im.getNotice().setReleaseName(im.getUser().getName());
	  }
	
	public Object insertBeforDeal(notice p) throws Exception {
		p.setReleaseName(currentUser.getCurrentUser().getName());
		return null;
	}
	
	public Object updateBeforDeal(notice p) throws Exception {
		p.setReleaseName(currentUser.getCurrentUser().getName());
		return null;
	}
}
