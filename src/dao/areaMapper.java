package dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

public Map<String,Integer> getAreasOfPolic(Integer policeID){
	String hql="select area.name,area.id from pojo.area area where area.policeID=:policeID";
	Query query=getSession().createQuery(hql);
    query.setInteger("policeID", policeID);
    List<Object[]> record=query.list();
    Map<String,Integer> results=new HashMap<String,Integer>(0);
     if(ListUtil.isNotEmpty(record)){
    	 for(Object[] objs:record){
    		 results.put((String)objs[0],(Integer)objs[1]);
    	 }
     }
     return results;
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

	/*public List<area> getBasic(areaQueryParams map) throws Exception {

		//String hql = "select new pojo.area(area,photo) from pojo.area area left join area.photos photo where  area.photos[0]=photo and (:areaID IS NULL or area.id=:areaID) and (:policeID = :fjid or :policeID=area.policeID) and area.isDelete != true";
		String hql = "select new pojo.area(area,photo) from pojo.area area left join area.photos photo where (photo= area.photos[0] or photo is null) and (:areaID IS NULL or area.id=:areaID) and (:policeID = :fjid or :policeID=area.policeID) and area.isDelete != true";
		
		if (map.getOrderBy() != null) {
			hql = hql + " order by " + map.getOrderBy();
		}
		org.hibernate.Query query = getSession().createQuery(hql);
		if (map.getPage() != null && map.getPage() > 0) {
			query.setFirstResult((map.getPage() - 1) * map.getPageSize());
			query.setMaxResults(map.getPageSize());
			// query.setMaxResults(1);
			// System.out.println("pageSize"+map.getPageSize());
		}
		if (map.getBasicQueryCondition() != null) {
			query.setProperties(map);
		}
		return query.list();
	}
*/
}
