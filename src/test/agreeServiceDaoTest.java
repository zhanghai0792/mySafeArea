package test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import pojo.agree;
import pojo.interaction;
import servicesDao.agreeServiceDao;
import servicesDao.interactionServiceDao;
import servicesDao.replyServiceDao;

public class agreeServiceDaoTest {

	public static void main(String[] args) throws Exception {
		delete();

	}
public static void add() throws Exception{
	 ApplicationContext application=new ClassPathXmlApplicationContext("applicationContext.xml");
		agreeServiceDao dao=(agreeServiceDao)application.getBean("agreeServiceDao");
		agree agree=new agree();
		agree.setAgreeID(2);
		agree.setAgreeTime(new Date());
		agree.setInteractionID(2);
		agree.setIsDelete(false);
		dao.save(agree);
          
}
public static void delete() throws Exception{
	 ApplicationContext application=new ClassPathXmlApplicationContext("applicationContext.xml");
		agreeServiceDao dao=(agreeServiceDao)application.getBean("agreeServiceDao");
		agree agree=new agree();
		agree.setId(5);
		agree.setInteractionID(2);
		dao.delete(agree);
         
}

}
