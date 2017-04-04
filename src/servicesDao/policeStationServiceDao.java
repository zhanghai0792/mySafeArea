package servicesDao;

import org.springframework.stereotype.Service;

import dao.policeStationMapper;
import dao.query.policeStationQueryParams;
import pojo.policeStation;
@Service
public class policeStationServiceDao extends serviceDaoTemplate<policeStation, policeStationMapper,policeStationQueryParams>{
	public Integer getFJId(String fjName)throws Exception{
		return dao.getFJId(fjName);
	}
}
