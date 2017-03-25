package test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import controller.loginHomeController;
import controller.userLogin.currentUser;
import dao.query.interactionQueryParams;
import pojo.interaction;
import pojo.user;
import servicesDao.interactionServiceDao;
import servicesDao.replyServiceDao;
import util.JsonUtil;

public class homeServiceDaoTest {

	public static void main(String[] args) throws Exception {
		home();

	}
public static void home() throws Exception{
	 ApplicationContext application=new ClassPathXmlApplicationContext("applicationContext.xml");
	 loginHomeController controller=(loginHomeController)application.getBean("loginHomeController");
	  user user=new user();
	  user.setId(1);
	 currentUser.login(user);
		
		//	System.err.println(JsonUtil.getJsonString(controller.home()));
 }
}
