package test;

import java.util.Date;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import dao.query.shopQueryParams;
import pojo.shop;
import servicesDao.houseServiceDao;
import servicesDao.shopServiceDao;
import util.JsonUtil;
import util.StringUtil;

public class shopServiceDaoTest {

	public static void main(String[] args) throws Exception {
		/*add();*/
		query();
	}
  public static void add()throws Exception{
	  ApplicationContext application=new ClassPathXmlApplicationContext("applicationContext.xml");
		shopServiceDao dao=(shopServiceDao)application.getBean("shopServiceDao");
          shop shop=new pojo.shop();
          shop.setAdderID(2);
          shop.setAddress("九江东路999号02铺");
          shop.setAreaID(2);
          shop.setBusinessScope("安保业");
          shop.setCardID("33333333XXX");
          shop.setCreateDate(new Date());
          shop.setName("九江开锁门市");
          shop.setOwner("黄小花");
          shop.setPhone("1233444555");
          shop.setPinYin(StringUtil.getPY(shop.getName()));
          shop.setIsDelete(false);
          dao.save(shop);
          System.out.println("OK");
          
		
  }
  
  public static void query() throws Exception{
	  ApplicationContext application=new ClassPathXmlApplicationContext("applicationContext.xml");
		shopServiceDao dao=(shopServiceDao)application.getBean("shopServiceDao");
          shopQueryParams query=new shopQueryParams();
          query.setCondition("ks");
          query.setAreaID(1);
         
          List<shop> shops=dao.getDetail(query);
          System.out.println(JsonUtil.getJsonString(shops));
		
  }
}
