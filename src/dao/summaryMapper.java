package dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.stereotype.Repository;

import dao.query.summaryQueryParams;
import pojo.summaryPojo;
import util.JsonUtil;
import util.ListUtil;
@Repository
public class summaryMapper extends basicDaoImpl<summaryPojo, summaryQueryParams>{
    public List<summaryPojo> summaryMonthAndHistroy(List<Integer> areaIds,Class queryClass,Date startMonth)throws Exception{
    	String pack_class=queryClass.getSimpleName();
    	//String hql="( select vo.areaID as AareaID,vo.id as pid, 1 as pHistroy,(vo.createDate>=:startMonth?1:0) as pMonth, area.name as AareaName from "+pack_class+" vo ,pojo.area area where vo.areaID=area.id";
    	//String hql="( select new pojo.tempChange(vo.areaID,area.name,(vo.createDate>=:startMonth ? 1 : 0 )) from "+pack_class+" vo ,pojo.area area where vo.areaID=area.id";
    	String hql="( select vo.areaID as areaID, 1 as sourceData,case when vo.createDate>=:startMonth then 1 else 0 end as currentMonth, area.name as areaName from "+pack_class+" vo ,area area where vo.areaID=area.id";
    	if(ListUtil.isNotEmpty(areaIds)){
        	 hql=hql+" and area.id in(:ids) ";
         }
         hql=hql+" and vo.isDelete!=true ) as newTable";
         
         //String sumHql="select new pojo.summaryPojo(newTable.AareaID,newTable.AareaName,sum(newTable.pHistroy),sum(newTable.pMonth)) from "+hql+" group by newTable.AareaID";
         //String sumHql="select new pojo.summaryPojo(pojo.tempChange.areaID,pojo.tempChange.areaName,sum(pojo.tempChange.sourceData),sum(pojo.tempChange.currentMonth)) from "+hql+" group by pojo.tempChange.areaID";
         String sumHql="select areaID,areaName,sum(sourceData) as historyTotal,sum(newTable.currentMonth) as currentMonth from "+hql+" group by newTable.areaID"; 
         SQLQuery query=getSession().createSQLQuery(sumHql);
         query.setDate("startMonth", startMonth);
         if(ListUtil.isNotEmpty(areaIds)){
        	 query.setParameterList("ids", areaIds);
         }
         query.addScalar("areaID", StandardBasicTypes.INTEGER).addScalar("areaName",StandardBasicTypes.STRING).addScalar("historyTotal",StandardBasicTypes.LONG).addScalar("currentMonth",StandardBasicTypes.LONG);
         //query.addEntity(summaryPojo.class);
         List<Object[]> objects=query.list();
         if(ListUtil.isNotEmpty(objects)){
        	 List<summaryPojo> sums=new ArrayList<summaryPojo>(0);
        	 for(Object[] objs:objects){
        		 sums.add(new summaryPojo(objs));
        	 }
        	 return sums;
         }
    	System.err.println(JsonUtil.getJsonString(query.list()));
    	return null;
    }
}
