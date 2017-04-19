package test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import pojo.agree;
import pojo.interaction;
import pojo.version1;
import servicesDao.agreeServiceDao;
import servicesDao.interactionServiceDao;
import servicesDao.replyServiceDao;
import servicesDao.versionServiceDao;
import util.JsonUtil;

public class CopyOfagreeServiceDaoTest {

	public static void main(String[] args) throws Exception {
		add();

	}
public static void add() throws Exception{
	 ApplicationContext application=new ClassPathXmlApplicationContext("applicationContext.xml");
	 versionServiceDao dao=(versionServiceDao)application.getBean("versionServiceDao");
		
		version1 version=dao.getLastVersion();
		System.out.println(JsonUtil.getJsonString(version));
          
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
