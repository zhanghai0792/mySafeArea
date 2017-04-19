package controller;

import org.aspectj.org.eclipse.jdt.core.dom.SuperConstructorInvocation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import controller.userLogin.currentUser;
import dao.query.carQueryParams;
import dao.query.newsQueryParams;
import json.jsonResult;
import pojo.interaction;
import pojo.news;
import servicesDao.newsServiceDao;

@Controller
@RequestMapping("/NewsServlet")
public class newsController extends controllerTemplate<news, newsServiceDao, newsQueryParams>{

	
	protected jsonResult query(newsQueryParams im) throws Exception {
		if(im.getNewsId()!=null){
			//详细查看某条
			news n=serviceDao.getById(im.getNewsId());
			if(n!=null){
				return new jsonResult(n,"成功");
			}else{
				return new jsonResult(false, "没有id["+im.getNewsId()+"]的新闻");
			}
		}else{
			//分页查看前几条大概
			return serviceDao.getPagesResultBasic(im);
		}
	}
	public Object insertBeforDeal(news p) throws Exception {
		p.setReleaseName(currentUser.getCurrentUser().getName());
		return super.insertBeforDeal(p);
	}
	
	public Object updateBeforDeal(news p) throws Exception {
		p.setReleaseName(currentUser.getCurrentUser().getName());
		return super.updateBeforDeal(p);
	}
	
}
