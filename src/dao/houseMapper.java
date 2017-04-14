package dao;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

import dao.query.houseQueryParams;
import dao.query.interactionQueryParams;
import pojo.car;
import pojo.house;
import pojo.interaction;
import pojo.reply;
import pojo.resident;
import util.ListUtil;
@Repository
public class houseMapper extends basicDaoImpl<house, houseQueryParams>{

	
	public house selectByPrimaryKey(Integer id) throws Exception {
		String hql="select distinct house from pojo.house house left join fetch house.residents where house.id=:id";
	Query query=getSession().createQuery(hql);
	query.setInteger("id",id);
		return (house)query.uniqueResult();
	}

	//插入记录后是否要做特殊处理，在子类中覆盖该方法
			protected void insertAfterDeal(house record)throws Exception{
				if(record.getAreaID()!=null)
				 update(record);
			}
			 //更新记录后是否要做特殊处理，在子类中覆盖该方法
			protected void updateAfterDeal(house record)throws Exception{
				if(record.getAreaID()!=null)
				update(record);
				}
			private int update(house record)throws Exception{
				//String hql="update car car,area area, user user  set car.areaName=area.name,car.adderName=user.name  where car.id=:pojoID and car.areaID=area.id and car.adderID=user.id";
				String hql="update house house,area area set house.areaName=area.name where house.id=:pojoID and house.areaID=area.id";
				SQLQuery query=getSession().createSQLQuery(hql);
				query.setInteger("pojoID",record.getId());
				return query.executeUpdate();
			}
	
			public List<house> getBasic_noResident(houseQueryParams map) throws Exception {	
				List<house> houses=super.getBasic(map);
				Set<resident> rs=new HashSet<resident>(0);
				if(ListUtil.isNotEmpty(houses)){
					house h=null;
					Iterator<house> hi=houses.iterator();
					while(hi.hasNext()){
						h=hi.next();
						h.setResidents(rs);
					}
				}
				return houses;
			}
			
			
			public Map<String,Integer> getHouseOfPolic(Integer policeID){
				String hql="select distinct house.address,house.id from pojo.house house where house.policeID=:policeID";
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
			
}
