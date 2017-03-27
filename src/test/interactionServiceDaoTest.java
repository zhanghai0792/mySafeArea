package test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import controller.userLogin.currentUser;
import dao.query.interactionQueryParams;
import pojo.interaction;
import pojo.user;
import servicesDao.interactionServiceDao;
import servicesDao.replyServiceDao;
import util.JsonUtil;

public class interactionServiceDaoTest {

	public static void main(String[] args) throws Exception {
		query();

	}
public static void add() throws Exception{
	 ApplicationContext application=new ClassPathXmlApplicationContext("applicationContext.xml");
		interactionServiceDao dao=(interactionServiceDao)application.getBean("interactionServiceDao");
		interaction inter=new interaction();
		inter.setAgreeNum(0);
		inter.setContent("会上，党委副书记、校长刘晓东就抓好我校领导干部报告个人有关事项工作提出三点要求，一是要提高认识，深刻领会报告个人有关事项工作的重要意义。领导干部个人事项报告工作是推进全面从严治党、推进国家治理体系和治理能力现代化的有力武器。二是要深入学习，准确把握两项法规新精神新要求。《领导干部报告个人有关事项规定》和《领导干部个人有关事项报告查核结果处理办法》的出台实施，是贯彻以习近平同志为核心的党中央坚定推进全面从严治党部署要求的重要举措；是总结党的十八大以来贯彻执行报告制度实践经验的制度成果；是与时俱进解决新情况新问题的现实需要。学校各级领导干部务必要提高政治站位和思想认识，深刻领会两项法规的出台实施对进一步严明党的政治纪律和组织纪律、从严管理监督干部的重要意义。三是要如实填报，扎实推进两项法规的执行。领导干部应对填报内容的真实性和完整性负责，对无正当理由不按时报告、漏报或者隐瞒不报的，学校党委将根据情节轻重，严格按照两项法规有关规定处理，隐瞒不报情节较重或者经查核发现涉嫌其他违纪问题的，要追究其纪律责任。");
        inter.setIsDelete(false);
        inter.setReleaseID(1);
        inter.setReleaseTime(new Date());
        inter.setTitle("互动测试1");
          List<String> images=new ArrayList<String>(0);
          images.add("互动图片1.jpg");
          images.add("互动图片2.jpg");
          images.add("互动图片3.jpg");
          inter.setPhotos(images);
          dao.save(inter);
}
 public static void query()throws Exception{
	 
		 ApplicationContext application=new ClassPathXmlApplicationContext("applicationContext.xml");
			interactionServiceDao dao=(interactionServiceDao)application.getBean("interactionServiceDao");
			interactionQueryParams query=new interactionQueryParams();
		//query.setInteractionID(3);
			query.setPage(1);
			query.setPageSize(10);
			user u=new user();
			u.setId(1);
			currentUser.login(u);
			List<interaction> is=dao.getBasic(query);
		
			System.err.println(JsonUtil.getJsonString(is));
 }
}
