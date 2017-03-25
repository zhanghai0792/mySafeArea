package test;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;



import pojo.user;
import pojo.house;
import pojo.resident;
import servicesDao.areaServiceDao;
import servicesDao.houseServiceDao;
import servicesDao.interactionServiceDao;
import servicesDao.userServiceDao;
import util.JsonUtil;
import util.StringUtil;

public class userServiceDaoTest {

	public static void main(String[] args) {

	ApplicationContext application=new ClassPathXmlApplicationContext("applicationContext.xml");
	userServiceDao dao=(userServiceDao)application.getBean("userServiceDao");

	
     try {
    	user user=new pojo.user();
    	user.setAreaID(2);
    	user.setHeader("user_img.jpg");
    	user.setIsDelete(false);
        user.setName("张三");
        user.setPassword("56789");
        user.setType(1);
        user.setPhone("18679229291");
        dao.save(user);/**/
       /* System.out.println(dao.isRegister("18679229290"));*/
     } catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

  System.out.println("ok");
		
		/*addHouse();*/
	}
	
	public static void add() {

		ApplicationContext application=new ClassPathXmlApplicationContext("applicationContext.xml");
		userServiceDao dao=(userServiceDao)application.getBean("userServiceDao");

		
	     try {
	    	user user=new pojo.user();
	    	user.setAreaID(1);
	    	user.setHeader("myuser_header1.png");
	    	user.setIsDelete(false);
	        user.setName("曹鹏飞");
	        user.setPassword("123456");
	        user.setType(1);
	        user.setPhone("18679229291");
	        dao.save(user);
	     } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	  System.out.println("ok");
			
			/*addHouse();*/
		}	
}
