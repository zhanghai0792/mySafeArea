package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import controller.userLogin.currentUser;

import dao.query.versionQueryParams;
import json.jsonResult;
import pojo.version1;

import servicesDao.versionServiceDao;
@Controller
@RequestMapping("/VersionServlet")
public class versionController extends controllerTemplate<version1, versionServiceDao, versionQueryParams>{

	@Override
	protected jsonResult query(versionQueryParams im) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
    @RequestMapping("/getVersion")
    @ResponseBody
    public jsonResult get(){
    	version1 version=serviceDao.getLastVersion();
    	return new jsonResult(version, "ok");
    }

}
