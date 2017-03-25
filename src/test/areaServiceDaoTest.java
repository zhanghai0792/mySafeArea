package test;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.sun.org.apache.bcel.internal.generic.ARRAYLENGTH;

import dao.query.areaQueryParams;
import pojo.area;
import servicesDao.areaServiceDao;
import servicesDao.interactionServiceDao;
import util.JsonUtil;

public class areaServiceDaoTest {

	public static void main(String[] args) throws Exception {

	ApplicationContext application=new ClassPathXmlApplicationContext("applicationContext.xml");
	areaServiceDao dao=(areaServiceDao)application.getBean("areaServiceDao");

	areaQueryParams queryParams=new areaQueryParams();
    queryParams.setPage(1);
    queryParams.setPageSize(4);
    queryParams.setAreaID(1);;
   List<area> list=dao.getDetail(queryParams);
	System.out.println(JsonUtil.getJsonString(list));

  System.out.println("ok");
	}
	
	public static void querygetDtail() throws Exception{
		ApplicationContext application=new ClassPathXmlApplicationContext("applicationContext.xml");
		areaServiceDao dao=(areaServiceDao)application.getBean("areaServiceDao");
	     areaQueryParams queryParams=new areaQueryParams();
	      queryParams.setPage(1);
	      queryParams.setPageSize(4);
	     List<area> list=dao.getDetail(queryParams);
		System.out.println(JsonUtil.getJsonString(list));

	  System.out.println("ok");
	}
	
	public static void main1() throws Exception {
		
	ApplicationContext application=new ClassPathXmlApplicationContext("applicationContext.xml");
	areaServiceDao dao=(areaServiceDao)application.getBean("areaServiceDao");
     System.out.println(dao.count(new areaQueryParams()));

  System.out.println("ok");
	}
	
 public static void savearea() throws Exception{
	 ApplicationContext application=new ClassPathXmlApplicationContext("applicationContext.xml");
		areaServiceDao dao=(areaServiceDao)application.getBean("areaServiceDao");
	     area record=new area();
	     record.setIntroduction("国家中心城市的正式提出，最早是在2005年。当时，中国城市规划设计研究院（以下简称中规院）受建设部（现住建部）委托，在编制《全国城镇体系规划（2006-2010年）》过程中，首次使用了这个概念。不过当年并没有引起广泛的关注。全国城镇体系规划》是城乡规划的纲领性文件，是国家推进新型城镇化发展的综合空间规划平台。依据《城乡规划法》，由国务院城乡规划主管部门会同国务院有关部门，定期组织编制该规划，用于指导省域城镇体系规划、城市总体规划的编制。");
		 record.setName("测试小区2--学院小区");
		 List<String> photos=new ArrayList<String>(0);
		 photos.add("http://www.jju.edu.cn/01.jpg");
		 photos.add("http://www.jju.edu.cn/02.jpg");
		 photos.add("http://www.jju.edu.cn/03.jpg");
	     record.setPhotos(photos);
	     record.setPoliceStation("七里湖派出所");
	     record.setIsDelete(false);
		 dao.save(record);
 }
}
