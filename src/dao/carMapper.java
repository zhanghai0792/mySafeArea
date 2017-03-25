package dao;



import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

import dao.query.carQueryParams;
import pojo.car;
@Repository
public class carMapper extends basicDaoImpl<car,carQueryParams>{
	 //插入记录后是否要做特殊处理，在子类中覆盖该方法
		protected void insertAfterDeal(car record)throws Exception{
			if(record.getAreaID()!=null)
			update(record);
		}
		 //更新记录后是否要做特殊处理，在子类中覆盖该方法
		protected void updateAfterDeal(car record)throws Exception{
			if(record.getAreaID()!=null)
			update(record);
			}
		private int update(car record)throws Exception{
			//String hql="update car car,area area, user user  set car.areaName=area.name,car.adderName=user.name  where car.id=:pojoID and car.areaID=area.id and car.adderID=user.id";
			String hql="update car car,area area set car.areaName=area.name where car.id=:pojoID and car.areaID=area.id";
			SQLQuery query=getSession().createSQLQuery(hql);
			query.setInteger("pojoID",record.getId());
			return query.executeUpdate();
		}
}
