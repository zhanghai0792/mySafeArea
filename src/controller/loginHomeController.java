package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import controller.userLogin.currentUser;
import dao.query.interactionQueryParams;
import dao.query.newsQueryParams;
import dao.query.noticeQueryParams;
import dao.query.userQueryParams;
import json.MyObjectMapper;
import json.jsonResult;
import pojo.interaction;
import pojo.user;
import servicesDao.agreeServiceDao;
import servicesDao.interactionServiceDao;
import servicesDao.newsServiceDao;
import servicesDao.noticeServicDao;
import servicesDao.userServiceDao;
import util.ListUtil;
import util.StringUtil;

@Controller
public class loginHomeController {
	@Autowired
	userServiceDao serviceDao;
	@Autowired
	protected MyObjectMapper myObjectMapper;
    @Autowired
	newsServiceDao newsServiceDao;
	@Autowired
	noticeServicDao noticeServiceDao;
	@Autowired
	interactionServiceDao interactionServiceDao;
    @Autowired
    agreeServiceDao agreeServiceDao;
 
    @RequestMapping("/HomepageServlet")
	@ResponseBody
	public Map home(@RequestParam("params") String params)throws Exception{
		if (StringUtil.isEmpty(params)) {
			Map<String,Object> map=new HashMap<String,Object>(0);
			map.put("success", false);
			map.put("msg", "操作对象未传递");
		}
		userQueryParams data = (userQueryParams) myObjectMapper.readValue(params, userQueryParams.class);
		System.err.println(myObjectMapper.writeValueAsString(data));
		jsonResult json = null;
		if (data.getUser() == null) {
			Map<String,Object> map=new HashMap<String,Object>(0);
			map.put("success", false);
			map.put("msg",  "没有使用权限,请重新登录");
			return map;
		}
		// 移动用户进入web系统
		currentUser.login(data.getUser());
		
		Map<String,Object> result=new HashMap<String,Object>(0);
		result.put("success", true);
		Map<String,Object> datas=new HashMap<String,Object>(0);
		newsQueryParams newsQuery=new newsQueryParams();
		newsQuery.setPage(1);
		newsQuery.setPageSize(3);
		datas.put("news", newsServiceDao.getDetail(newsQuery));
		noticeQueryParams noticeQuery=new noticeQueryParams();
		noticeQuery.setPage(1);
		noticeQuery.setPageSize(3);
		datas.put("notices", noticeServiceDao.getBasic(noticeQuery));
		interactionQueryParams interQuery=new interactionQueryParams();
		interQuery.setPage(1);
		interQuery.setPageSize(2);
		List<interaction> interactions=interactionServiceDao.getBasic(interQuery);
		/*List<Integer> ids=new ArrayList<Integer>(0);
		if(ListUtil.isNotEmpty(interactions)){
			for(interaction i:interactions)
				ids.add(i.getId());
		Map<Integer,Long> isagrees=agreeServiceDao.currentIsAgreeInteraction(ids,currentUser.getCurrentUser().getId());
		for(interaction i:interactions)
			if(isagrees.get(i.getId())!=null)
			i.setIsAgree(isagrees.get(i.getId())>0);
	}*/
		 datas.put("interactions", interactions);
		result.put("datas", datas);
		return result;
	}
	
	
	public userServiceDao getServiceDao() {
		return serviceDao;
	}

	public void setServiceDao(userServiceDao serviceDao) {
		this.serviceDao = serviceDao;
	}

	public MyObjectMapper getMyObjectMapper() {
		return myObjectMapper;
	}

	public void setMyObjectMapper(MyObjectMapper myObjectMapper) {
		this.myObjectMapper = myObjectMapper;
	}

	public newsServiceDao getNewsServiceDao() {
		return newsServiceDao;
	}

	public void setNewsServiceDao(newsServiceDao newsServiceDao) {
		this.newsServiceDao = newsServiceDao;
	}

	public noticeServicDao getNoticeServiceDao() {
		return noticeServiceDao;
	}

	public void setNoticeServiceDao(noticeServicDao noticeServiceDao) {
		this.noticeServiceDao = noticeServiceDao;
	}

	public interactionServiceDao getInteractionServiceDao() {
		return interactionServiceDao;
	}

	public void setInteractionServiceDao(interactionServiceDao interactionServiceDao) {
		this.interactionServiceDao = interactionServiceDao;
	}

	public agreeServiceDao getAgreeServiceDao() {
		return agreeServiceDao;
	}

	public void setAgreeServiceDao(agreeServiceDao agreeServiceDao) {
		this.agreeServiceDao = agreeServiceDao;
	}

}
