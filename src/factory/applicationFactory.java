package factory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pojo.policeStation;
import servicesDao.policeStationServiceDao;
import util.AppConfig;

@Component
public class applicationFactory {
	@Autowired
  private policeStationServiceDao policeStationServiceDao;
	/*@Autowired
	private  qiyunThread qiyunThread;*/
	
  public static Integer fjId;//分局Id
  public static Map<Integer,String> polices=new HashMap<Integer,String>(0);
  @PostConstruct
  public void init() throws Exception{
	  //fjId=policeStationServiceDao.getFJId(AppConfig.fjName);
	  //System.out.println("获得的分局id"+fjId);
	 // qiyunThread.start();
	  List<policeStation> pos=policeStationServiceDao.getPolices();
	  for(policeStation p:pos){
		  polices.put(p.getId(), p.getName());
		  if(AppConfig.fjName.equals(p.getName()))
			  fjId=p.getId();
	  }
  }


  
public policeStationServiceDao getPoliceStationServiceDao() {
	return policeStationServiceDao;
}

public void setPoliceStationServiceDao(policeStationServiceDao policeStationServiceDao) {
	this.policeStationServiceDao = policeStationServiceDao;
}

  
}
