package controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import controller.userLogin.currentUser;
import dao.query.carQueryParams;
import dao.query.houseQueryParams;
import json.jsonResult;
import pojo.car;
import pojo.house;
import servicesDao.houseServiceDao;
import util.ListUtil;
import util.StringUtil;

@Controller
@RequestMapping("/HouseServlet")
public class houseController extends controllerTemplate<house, houseServiceDao, houseQueryParams>{

	
	protected jsonResult query(houseQueryParams im) throws Exception {
        
        	 //房屋的基本信息查询
        	 return getPagesBasic(im);
     

	}
	
	public void addPY(house houser){
		if(houser!=null&&StringUtil.isNotEmpty(houser.getOwner())){
			houser.setPinYin(StringUtil.getPY(houser.getOwner()));
		}
	}
	
	
	public Object insertBeforDeal(house p) throws Exception {
		p.setAdderName(currentUser.getCurrentUser().getName());
		return null;
	}
	
	public Object updateBeforDeal(house p) throws Exception {
		p.setAdderName(currentUser.getCurrentUser().getName());
		return super.updateBeforDeal(p);
	}

}
