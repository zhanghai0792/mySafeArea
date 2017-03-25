package test;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import dao.query.carQueryParams;
import pojo.user;
import pojo.car;
import pojo.house;
import pojo.resident;
import servicesDao.areaServiceDao;
import servicesDao.carServiceDao;
import servicesDao.houseServiceDao;
import servicesDao.interactionServiceDao;
import servicesDao.userServiceDao;
import util.JsonUtil;
import util.StringUtil;

public class carServiceDaoTest {

	public static void main(String[] args) {
         add();
	

}
	
	public static void add() {

		ApplicationContext application=new ClassPathXmlApplicationContext("applicationContext.xml");
		carServiceDao dao=(carServiceDao)application.getBean("carServiceDao");
	     car car=new car();
	     car.setAdderID(1);
	     car.setAreaID(4);
	     car.setBrand("奇瑞 QQ");
		 car.setCardID("429009990000220000");
		 car.setCarNum("赣M00T98");
		 car.setColor("测试");
		 car.setCreateDate(new Date());
		 car.setIsDelete(false);
		 car.setOwner("车辆添加测试_1");
		 car.setPhone("phone1234");
		 car.setPinYin(StringUtil.getPY(car.getOwner()));
		 car.setPlateNum("车架编号0099999");
	     try {
	    	dao.save(car);
	     } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	  System.out.println("ok");
			
			/*addHouse();*/
		}
	
	public static void query(){
		ApplicationContext application=new ClassPathXmlApplicationContext("applicationContext.xml");
		carServiceDao dao=(carServiceDao)application.getBean("carServiceDao");
	     /*car car=new car();
	     car.setAdderID(1);
	     car.setAreaID(2);
	     car.setBrand("大众  宝来");
		 car.setCardID("车主000SSSSS");
		 car.setCarNum("赣G0bm0s1");
		 car.setColor("蓝");
		 car.setCreateDate(new Date());
		 car.setIsDelete(false);
		 car.setOwner("车辆测试-车主3");
		 car.setPhone("车主电话1234");
		 car.setPinYin(StringUtil.getPY(car.getOwner()));
		 car.setPlateNum("车架号CEJAHAO123");*/
		
	     try {
	    	 carQueryParams query=new carQueryParams();
	    		query.setCondition("bm0");
	    		 List<car> cars=dao.getBasic(query);
	    		 
	    		 System.err.println(JsonUtil.getJsonString(cars));
	     } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	  System.out.println("ok");
	}
	
}
