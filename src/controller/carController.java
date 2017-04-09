package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import controller.userLogin.currentUser;
import dao.query.carQueryParams;
import dao.query.interactionQueryParams;
import json.jsonResult;
import pojo.car;
import servicesDao.carServiceDao;
import util.StringUtil;
@Controller
@RequestMapping("/CarServlet")
public class carController extends controllerTemplate<car, carServiceDao, carQueryParams>{

	
	protected jsonResult query(carQueryParams im) throws Exception {
		 return getPagesBasic(im);
	}
   /*覆盖模板中的方法，这样此pojo类就具有姓名添加拼音的功能*/
	public void addPY(car car){
		if(car!=null&&StringUtil.isNotEmpty(car.getOwner())){
			car.setPinYin(StringUtil.getPY(car.getOwner()));
		}
	}
/*	protected void androidInsertTemplateBefor(carQueryParams im){
		   im.getCar().setAdderName(im.getUser().getName());
	  }*/
	
	public Object insertBeforDeal(car p) throws Exception {
		p.setAdderName(currentUser.getCurrentUser().getName());
		return null;
	}
	
	public Object updateBeforDeal(car p) throws Exception {
		p.setAdderName(currentUser.getCurrentUser().getName());
		return super.updateBeforDeal(p);
	}
	

}
