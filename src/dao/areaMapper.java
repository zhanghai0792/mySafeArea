package dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import dao.query.areaQueryParams;
import pojo.area;
import pojo.house;
import util.ListUtil;
@Repository
public class areaMapper extends basicDaoImpl<area,areaQueryParams>{


	public int deletesById(areaQueryParams map)throws Exception{
		
		return super.deletesObjects(map.getPojos());
	}


	
	public area selectByPrimaryKey(Integer id) throws Exception {
		String hql="select distinct area from pojo.area area left join fetch area.photos where area.id=:id";
		Query query=getSession().createQuery(hql);
		query.setInteger("id",id);
			return (area)query.uniqueResult();
	}
	public void setBasicHQLHead(){
		hqlSelectHead="select new pojo.area(area) from pojo.area area";
	}

	

}
