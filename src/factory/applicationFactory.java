package factory;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import servicesDao.policeStationServiceDao;
import util.AppConfig;

@Component
public class applicationFactory {
	@Autowired
  private policeStationServiceDao policeStationServiceDao;
	/*@Autowired
	private  qiyunThread qiyunThread;*/
	
  public static Integer fjId;//分局Id
  
  @PostConstruct
  public void init() throws Exception{
	  fjId=policeStationServiceDao.getFJId(AppConfig.fjName);
	  //System.out.println("获得的分局id"+fjId);
	 // qiyunThread.start();
  }


  
public policeStationServiceDao getPoliceStationServiceDao() {
	return policeStationServiceDao;
}

public void setPoliceStationServiceDao(policeStationServiceDao policeStationServiceDao) {
	this.policeStationServiceDao = policeStationServiceDao;
}

  
}
