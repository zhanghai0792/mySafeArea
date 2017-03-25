package test;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import dao.query.newsQueryParams;
import pojo.news;
import servicesDao.newsServiceDao;
import servicesDao.userServiceDao;
import util.JsonUtil;
import util.StringUtil;

public class newsServiceDaoTest {

	public static void main(String[] args) {

	ApplicationContext application=new ClassPathXmlApplicationContext("applicationContext.xml");
	newsServiceDao dao=(newsServiceDao)application.getBean("newsServiceDao");
     try {
    	
    	dao.delete(3);
     } catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

  System.out.println("ok");

}
	public static void page() {

		ApplicationContext application=new ClassPathXmlApplicationContext("applicationContext.xml");
		newsServiceDao dao=(newsServiceDao)application.getBean("newsServiceDao");
	     
		
	     try {
	    	 newsQueryParams newsQuery=new newsQueryParams();
	    	 newsQuery.setPage(1);
	    	 List<news> newsies=dao.getDetail(newsQuery);
	System.out.println(JsonUtil.getJsonString(newsies));
	     } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	  System.out.println("ok");

	}
	
	public static void addMain() {

		ApplicationContext application=new ClassPathXmlApplicationContext("applicationContext.xml");
		newsServiceDao dao=(newsServiceDao)application.getBean("newsServiceDao");
	     
		
	     try {
	    	news news=new pojo.news();
	    	news.setContent("新闻测试---test1-----test1-test1test1test1，我的news新闻内容的测试2，我的news新闻内容的测试3，我的news新闻内容的测试4，我的news新闻内容的测试5，我的news新闻内容的测试6，我的news新闻内容的测试7，我的news新闻内容的测试8，我的news新闻内容的测试9，我的news新闻内容的测试10，");
	    	news.setHeadlineMarker(true);
	        news.setIsDelete(false);    	 
	    	news.setReleaseID(2);
	    	news.setReleaseTime(new Date());
	    	news.setTitle("标题测试3");
	    	List<String> photos=new ArrayList<String>(0);
	    	photos.add("http://newsImg1.jpg");
	    	photos.add("http://newsImg2.jpg");

	    	news.setPhotos(photos);
	    	dao.save(news);
	     } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	  System.out.println("ok");

	}
	
	
	public static void update() {

		ApplicationContext application=new ClassPathXmlApplicationContext("applicationContext.xml");
		newsServiceDao dao=(newsServiceDao)application.getBean("newsServiceDao");
	     
		
	     try {
	    	
	    	 newsQueryParams newsQuery=new newsQueryParams();
	    	 newsQuery.setNewsId(1);
	    	 List<news> newsies=dao.getDetail(newsQuery);
	    	 news news=newsies.get(0);
	    	 news.getPhotos().add("我更新的图片1.jpg");
	    	 dao.updateAll(news);
	    	 //System.out.println(JsonUtil.getJsonString(newsies));
	   	 
	    	 
	     } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	  System.out.println("ok");

	}
	
}
