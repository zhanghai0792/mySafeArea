package controller;

import java.net.URLEncoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import dao.query.userQueryParams;
import json.jsonResult;
import pojo.user;
import servicesDao.userServiceDao;

@Controller
@RequestMapping("/user")
public class userController extends controllerTemplate<user, userServiceDao, userQueryParams> {

	
	protected jsonResult query(userQueryParams im) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
  @RequestMapping("/Login")	
  public String login(user user,HttpServletResponse response,HttpServletRequest request)throws Exception{
	  userQueryParams query = new userQueryParams();
		query.setUser(user);
		user userQuery = serviceDao.login(query);
		if(userQuery!=null){
		  String cookieValue=userQuery.getId()+"<>"+userQuery.getName()+"<>"+userQuery.getType();
		 String cookieZ= URLEncoder.encode(cookieValue,"UTF-8");
		  Cookie cookie=new Cookie("mySafeAreaUser", cookieZ);
		  cookie.setMaxAge(-1);//-1为内存保持
		//  System.err.println(request.getContextPath());
		  cookie.setPath(request.getContextPath()+"/");
		  response.addCookie(cookie);
		
		  return "controllTest";
		}else{
			return "index";
		}
  }
}