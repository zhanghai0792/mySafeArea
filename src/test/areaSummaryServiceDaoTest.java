package test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import pojo.agree;
import pojo.car;
import pojo.interaction;
import pojo.shop;
import servicesDao.agreeServiceDao;
import servicesDao.interactionServiceDao;
import servicesDao.replyServiceDao;
import servicesDao.summaryServiceDao;
import util.JsonUtil;
import util.StringUtil;

public class areaSummaryServiceDaoTest {

	public static void main(String[] args) throws Exception {
		get();

	}
public static void get() throws Exception{
	 ApplicationContext application=new ClassPathXmlApplicationContext("applicationContext.xml");
		summaryServiceDao dao=(summaryServiceDao)application.getBean("summaryServiceDao");
		List<Integer> ids=new ArrayList<Integer>(0);
		ids.add(1);
		System.err.println(JsonUtil.getJsonString(dao.summaryMonthAndHistroy(ids, car.class, StringUtil.getMonthBeginDay(new Date()))));
          
}


}
