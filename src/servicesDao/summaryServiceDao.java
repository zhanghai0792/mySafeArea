package servicesDao;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import dao.summaryMapper;
import dao.query.summaryQueryParams;
import pojo.summaryPojo;

@Service
public class summaryServiceDao extends serviceDaoTemplate<summaryPojo, summaryMapper, summaryQueryParams>{
	public List<summaryPojo> summaryMonthAndHistroy(List<Integer> areaIds,Class queryClass,Date startMonth)throws Exception{
		if(queryClass==null||startMonth==null)
			throw new Exception("没有选择比较月份或者比较类型");
		return dao.summaryMonthAndHistroy(areaIds, queryClass, startMonth);
	}
}
