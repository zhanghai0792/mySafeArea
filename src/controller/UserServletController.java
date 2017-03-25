package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import controller.userLogin.currentUser;
import dao.query.userQueryParams;
import json.jsonResult;
import pojo.user;
import servicesDao.userServiceDao;
import util.StringUtil;
@Controller
@RequestMapping("/UserServlet")
public class UserServletController extends controllerTemplate<user, userServiceDao, userQueryParams>{

	
	protected jsonResult query(userQueryParams im) throws Exception {
		
		return super.getPagesBasic(im);
	}
	
	public jsonResult login(@RequestParam("params") String userJson, Integer method) throws Exception {
		jsonResult json = null;
		if (StringUtil.isNotEmpty(userJson)) {
			//System.out.println(userJson);
			user user = myObjectMapper.readValue(userJson, user.class);
			userQueryParams query = new userQueryParams();
			query.setUser(user);
			user = serviceDao.login(query);
			if (user != null) {
				json = new jsonResult(user, "登录成功");
			} else {
				json = new jsonResult(false, "用户名或密码不正确");
			}
		} else {
			json = new jsonResult(false, "参数输入不正确");
		}
		return json;
	}
	

	public jsonResult deal(@RequestParam("method") Integer method, @RequestParam("params") String params) throws Exception {
		if (StringUtil.isEmpty(params)) {
			return new jsonResult(false, "操作对象未传递");
		}
		if(method==3){
			return login(params,method);
		}
		userQueryParams data = (userQueryParams) myObjectMapper.readValue(params, userQueryParams.class);
		System.err.println(myObjectMapper.writeValueAsString(data));
		jsonResult json = null;
		if (data.getUser() == null) {
			json = new jsonResult(false, "没有使用权限,请重新登录");
			return json;
		}
		// 移动用户进入web系统
		currentUser.login(data.getUser());
		Integer type = data.getUser().getType();
		if (method == null) {
			json = new jsonResult(false, "没有对应method操作");
		} else {
			if (method == 0) {
				//注册
				 if(serviceDao.isRegister(data.getUser().getPhone()))
					 return new jsonResult(false, data.getUser().getPhone()+"已被注册");
				json = androidInsertTemplate(data);
			} else if (method == 1) {
				// 更新
				json = androidUpdateTemplate(data);
			} else if (method == 2) {
				// 删除
				if (type != 1 && type != 2) {
					json = new jsonResult(false, "没有使用权限,请重新登录");
					return json;
				}
				json = androidDeleteTemplate(data);
			} else if (method == 3) {
				// 查询
				json = query(data);
			} else if (method == 4) {
				json = getPagesDetail(data);
			} else {
				json = new jsonResult(false, "没有对应method操作");
			}
		}
		// 移动用户离开web系统
		currentUser.exit();
		return json;
	}

}
