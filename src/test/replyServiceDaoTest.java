package test;

import java.util.Date;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import dao.query.shopQueryParams;
import pojo.reply;
import pojo.shop;
import servicesDao.houseServiceDao;
import servicesDao.replyServiceDao;
import servicesDao.shopServiceDao;
import util.JsonUtil;
import util.StringUtil;

public class replyServiceDaoTest {

	public static void main(String[] args) throws Exception {
		add();
		//query();
	}
  public static void add()throws Exception{
	  ApplicationContext application=new ClassPathXmlApplicationContext("applicationContext.xml");
		replyServiceDao dao=(replyServiceDao)application.getBean("replyServiceDao");
         reply reply=new reply();
         reply.setContent("关注2-2 的跟贴2");
         reply.setInteractionID(2);
         reply.setIsDelete(false);
         reply.setReplyerID(2);
         reply.setReplyTime(new Date());
         reply.setType(0);
        // reply.setReplyHeader("myHeader1.jpg");
         dao.save(reply);
          System.out.println("OK");		
  }
  
  public static void query() throws Exception{
	  ApplicationContext application=new ClassPathXmlApplicationContext("applicationContext.xml");
		replyServiceDao dao=(replyServiceDao)application.getBean("replyServiceDao");
     
          //System.out.println(JsonUtil.getJsonString(shops));
		
  }
}
