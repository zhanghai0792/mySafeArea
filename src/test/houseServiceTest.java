package test;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.sun.org.apache.bcel.internal.generic.ARRAYLENGTH;

import dao.query.areaQueryParams;
import dao.query.houseQueryParams;
import pojo.area;
import pojo.house;
import pojo.resident;
import servicesDao.areaServiceDao;
import servicesDao.houseServiceDao;
import servicesDao.interactionServiceDao;
import util.JsonUtil;
import util.StringUtil;

public class houseServiceTest {

	public static void main(String[] args) {

	ApplicationContext application=new ClassPathXmlApplicationContext("applicationContext.xml");
	houseServiceDao dao=(houseServiceDao)application.getBean("houseServiceDao");

	
     try {
    	
    	houseQueryParams query=new houseQueryParams();
    	 query.setHouseID(2);
    	 List<house> houses=dao.getBasic(query);	
    	 System.out.println(JsonUtil.getJsonString(houses));
    	 
    	 
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

  System.out.println("ok");
		
		/*addHouse();*/
	}
	
/*	public static void mainPages() {

		ApplicationContext application=new ClassPathXmlApplicationContext("applicationContext.xml");
		houseServiceDao dao=(houseServiceDao)application.getBean("houseServiceDao");	
	     try {
	    	
	    	 houseQueryParams query=new houseQueryParams();
	    	 query.setAreaID(1);
	    	
	    	 query.setPage(1);
	    	 query.setPageSize(1);
	    	 
	    	 List<house> houses=dao.getDetail(query);
	    	 System.out.println(JsonUtil.getJsonString(houses));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	  System.out.println("ok");
		}
	
	public static void addResident() {

		ApplicationContext application=new ClassPathXmlApplicationContext("applicationContext.xml");
		houseServiceDao dao=(houseServiceDao)application.getBean("houseServiceDao");
	     house house=new house();
	     house.setAdderID(1);
	     house.setAddress("汽车城1区");
	     house.setAreaID(1);
	     house.setCreateDate(new Date());
	     house.setIsDelete(false);
	     house.setOwner("房屋测试-户主张");
	     house.setPinYin(StringUtil.getPY(house.getOwner()));
	     house.setOwnerCardID("身份证000220011");
	     house.setPhone("18170238000");
	     house.setType(1);
		
	     try {
	    	 house house=dao.getById(1);
	    	 resident r1=new resident();
	    	 r1.setAdderID(1);
	    	 r1.setAdress("九江市前进东路1");
	    	 r1.setCardID("429005######1");
	    	 r1.setCreateDate(new Date());
	    	 r1.setHeader("myHeader1.jpg");
	    	 r1.setHouseID(1);
	    	 r1.setIsDelete(false);
	    	 r1.setName("王晓霞1");
	    	 r1.setNation("中国1");
	    	 r1.setPhone("188800001");
	    	 r1.setPinYin(StringUtil.getPY(r1.getName()));
	    	// house.getResidents().add(r1);
	    	 Set<resident> rs=new HashSet<resident>();
	    	 rs.add(r1);
	    	 r1=new resident();
	    	 r1.setAdderID(1);
	    	 r1.setAdress("九江市前进东路2");
	    	 r1.setCardID("429005######2");
	    	 r1.setCreateDate(new Date());
	    	 r1.setHeader("myHeader2.jpg");
	    	 r1.setHouseID(1);
	    	 r1.setIsDelete(false);
	    	 r1.setName("王晓霞2");
	    	 r1.setNation("中国2");
	    	 r1.setPhone("188800002");
	    	 r1.setPinYin(StringUtil.getPY(r1.getName()));
	    	 //house.getResidents().add(r1);
	    	 rs.add(r1);
	    	 house.setResidents(rs);
			dao.updateAll(house);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	  System.out.println("ok");
		}
	
	public static void addHouse(){
		ApplicationContext application=new ClassPathXmlApplicationContext("applicationContext.xml");
		houseServiceDao dao=(houseServiceDao)application.getBean("houseServiceDao");
	     house house=new house();
	     house.setAdderID(1);
	     house.setAddress("九江学院教工1村");
	     house.setAreaID(1);
	     house.setCreateDate(new Date());
	     house.setIsDelete(false);
	     house.setOwner("房屋测试-教工1");
	     house.setPinYin(StringUtil.getPY(house.getOwner()));
	     house.setOwnerCardID("360033");
	     house.setPhone("电话123444");
	     house.setType(2);
	     try {
			dao.save(house);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	  System.out.println("ok");
	}*/
}
