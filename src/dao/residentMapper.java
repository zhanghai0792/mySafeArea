package dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

import dao.query.residentQueryParams;
import pojo.house;
import pojo.resident;
@Repository
public class residentMapper extends basicDaoImpl<resident, residentQueryParams>{
     public void deleteResidentHouseInfo(house house)throws Exception{
    	 String hql="update pojo.resident resident set houseID=NULL where resident.houseID=:houseID";
    	 Query query=getSession().createQuery(hql);
    	 query.setInteger("houseID", house.getId());
    	  query.executeUpdate();
    	 
     }
     
     public  List<String> getPhotos(house house)throws Exception{
    	// String hql="update pojo.resident resident set houseID=NULL where resident.houseID=:houseID";
    	 String hql="select resident.header from pojo.resident resident where resident.houseID=:houseID";
    	 Query query=getSession().createQuery(hql);
    	 query.setInteger("houseID", house.getId());
    	return query.list();
    	 
     }
     
     protected void insertAfterDeal(resident record)throws Exception{
		 if(record.getHouseID()!=null)	
    	 update(record);
		}
		 //更新记录后是否要做特殊处理，在子类中覆盖该方法
		protected void updateAfterDeal(resident record)throws Exception{
			 if(record.getHouseID()!=null)	
			update(record);
			}
		private int update(resident record)throws Exception{
			//String hql="update car car,area area, user user  set car.areaName=area.name,car.adderName=user.name  where car.id=:pojoID and car.areaID=area.id and car.adderID=user.id";
			String hql="update resident resident,house house set resident.houseName=house.address where resident.id=:pojoID and resident.houseID=house.id";
			SQLQuery query=getSession().createSQLQuery(hql);
			query.setInteger("pojoID",record.getId());
			return query.executeUpdate();
		}
     
}
