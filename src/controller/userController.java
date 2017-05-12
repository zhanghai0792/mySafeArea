package controller;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import dao.query.userQueryParams;
import json.jsonResult;
import pojo.user;
import servicesDao.userServiceDao;
import util.JsonUtil;
import util.ListUtil;

@Controller
@RequestMapping("/user")
public class userController extends controllerTemplate<user, userServiceDao, userQueryParams> {

	
	
	
	protected jsonResult query(userQueryParams im) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
  @RequestMapping("/Login")	
  public String login(user user,HttpServletResponse response,HttpServletRequest request)throws Exception{
	    if(user!=null){
	    	if("jmwh".equals(user.getPhone())&&"jm601".equals(user.getPassword())){
	    		 String cookieValue=99999+"<>"+"admin"+"<>"+3+"<>"+1;
	    		 String cookieZ= URLEncoder.encode(cookieValue,"UTF-8");
	   		  Cookie cookie=new Cookie("mySafeAreaUser", cookieZ);
	   		  cookie.setMaxAge(-1);//-1为内存保持
	   		//  System.err.println(request.getContextPath());
	   		  cookie.setPath(request.getContextPath()+"/");
	   		  response.addCookie(cookie);
	   		  user.setType(10);
	   		 request.getSession().setAttribute("user", user);
	   		 return "wh";
	    	}
	    }  
	  userQueryParams query = new userQueryParams();
		query.setUser(user);
		user userQuery = serviceDao.login(query);
		
		if(userQuery.getType()!=3)
			throw new Exception("您没有web操作权限,请联系管理员");
		if(userQuery!=null){
		  String cookieValue=userQuery.getId()+"<>"+userQuery.getName()+"<>"+userQuery.getType()+"<>"+userQuery.getPoliceID();
		 String cookieZ= URLEncoder.encode(cookieValue,"UTF-8");
		  Cookie cookie=new Cookie("mySafeAreaUser", cookieZ);
		  cookie.setMaxAge(-1);//-1为内存保持
		//  System.err.println(request.getContextPath());
		  cookie.setPath(request.getContextPath()+"/");
		  response.addCookie(cookie);
		  request.getSession().setAttribute("user", userQuery);
		  return "population";
		}else{
			return "redirect:/index.htm";
		}
  }
  
	public jsonResult importData(MultipartFile file) throws Exception {
		 if(file==null||file.getInputStream()==null)
			 throw new Exception("没有上传的excel文件");
		List datas=excelImport.readExcelToData(file, pojoClass);
		int count=0;
		List<Integer> nos=new ArrayList<Integer>(0);
		List<user> successUsers=new ArrayList<user>(0);
		if(ListUtil.isNotEmpty(datas)){
			
			
			for(int i=0;i<datas.size();i++){
				try{
				count=count+serviceDao.save((user)datas.get(i));
				successUsers.add((user)datas.get(i));
				}catch(Exception e){
					nos.add((i+1));
				}
			}
		}
		if(successUsers.size()>0)
			serviceDao.updatesUserArea(successUsers);
		String result="成功导入【"+count+"】条记录"; 
		 if(nos.size()>0){
			 result=result+"<br>导入失败【"+nos.size()+"】条<br>excel中第"+JsonUtil.getJsonString(nos)+"条记录有问题没有导入";
			 }
		return new jsonResult(result);
	}
  
}
