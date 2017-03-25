package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import controller.userLogin.currentUser;
import dao.query.houseQueryParams;
import dao.query.residentQueryParams;
import json.jsonResult;
import pojo.car;
import pojo.house;
import pojo.resident;
import servicesDao.residentServiceDao;
import util.StringUtil;

@Controller
public class residentController extends controllerTemplate<resident,residentServiceDao,residentQueryParams>{

	
	protected jsonResult query(residentQueryParams im) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	public void addPY(resident resident){
		if(resident!=null&&StringUtil.isNotEmpty(resident.getName())){
			resident.setPinYin(StringUtil.getPY(resident.getName()));
		}
	}
	public Object insertBeforDeal(resident p) throws Exception {
		p.setAdderName(currentUser.getCurrentUser().getName());
		return null;
	}
	
	public Object updateBeforDeal(resident p) throws Exception {
		p.setAdderName(currentUser.getCurrentUser().getName());
		return null;
	}
}
