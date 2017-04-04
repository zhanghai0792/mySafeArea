package dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import dao.query.policeStationQueryParams;
import pojo.policeStation;
import util.ListUtil;

@Repository
public class policeStationMapper extends basicDaoImpl<policeStation, policeStationQueryParams>{
	public Integer getFJId(String fjName)throws Exception{
		String hql="select policeStation.id from pojo.policeStation policeStation where policeStation.name=:fjName";
		Query query=getSession().createQuery(hql);
		query.setString("fjName", fjName);
		//System.out.println("分局姓名="+fjName);
		List<Integer> ids=query.list();
		if(ListUtil.isEmpty(ids))
			throw new Exception("没有分局信息");
		return ids.get(0);
	}
	
}
