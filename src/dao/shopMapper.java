package dao;

import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

import dao.query.shopQueryParams;
import pojo.shop;
@Repository
public class shopMapper extends basicDaoImpl<shop, shopQueryParams>{
	protected void insertAfterDeal(shop record)throws Exception{
		 if(record.getAreaID()!=null)
		update(record);
	}
	 //更新记录后是否要做特殊处理，在子类中覆盖该方法
	protected void updateAfterDeal(shop record)throws Exception{
		 if(record.getAreaID()!=null)
		update(record);
		}
	private int update(shop record)throws Exception{
		//String hql="update car car,area area, user user  set car.areaName=area.name,car.adderName=user.name  where car.id=:pojoID and car.areaID=area.id and car.adderID=user.id";
		String hql="update shop shop,area area set shop.areaName=area.name where shop.id=:pojoID and shop.areaID=area.id";
		SQLQuery query=getSession().createSQLQuery(hql);
		query.setInteger("pojoID",record.getId());
		return query.executeUpdate();
	}
}
