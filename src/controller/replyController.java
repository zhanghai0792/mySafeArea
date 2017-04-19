package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import controller.userLogin.currentUser;
import dao.query.newsQueryParams;
import dao.query.replyQueryParams;
import json.jsonResult;
import pojo.notice;
import pojo.reply;
import servicesDao.replyServiceDao;

@Controller
@RequestMapping("/ReplyServlet")
public class replyController extends controllerTemplate<reply, replyServiceDao, replyQueryParams>{

	
	protected jsonResult query(replyQueryParams im) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	public Object insertBeforDeal(reply p) throws Exception {
		p.setReplyerName(currentUser.getCurrentUser().getName());
		p.setReplyHeader(currentUser.getCurrentUser().getHeader());
		return super.insertBeforDeal(p);
	}
	
	public Object updateBeforDeal(reply p) throws Exception {
		p.setReplyerName(currentUser.getCurrentUser().getName());
		p.setReplyHeader(currentUser.getCurrentUser().getHeader());
		return super.updateBeforDeal(p);
	}
	
	
	
}
