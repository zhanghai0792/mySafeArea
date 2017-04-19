package dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

import dao.query.userQueryParams;
import pojo.shop;
import pojo.user;
import util.ListUtil;
@Repository
public class userMapper extends basicDaoImpl<user,userQueryParams>{
    //注册返回true，没有方法false
	public boolean isRegister(String phone){
	   String hql="select count(user.id) from pojo.user user where user.phone=:phone";
	   Query query=getSession().createQuery(hql);
	   query.setString("phone", phone);
	   return ((Long)query.uniqueResult())>0;
   }

	public user login(String phone,String pwd) throws Exception{
		String hql="from pojo.user user where user.phone=:phone and user.password=:pwd";
		Query query=getSession().createQuery(hql);
		query.setString("phone", phone);
		query.setString("pwd", pwd);
		List<user> users=query.list();
		if(ListUtil.isEmpty(users)){
			throw new Exception("用户名或者密码不正确");
		}else{
			return users.get(0);
		}
		
	}
	
	
	protected void insertAfterDeal(user record)throws Exception{
		 if(record.getAreaID()!=null)
		update(record);
	}
	 //更新记录后是否要做特殊处理，在子类中覆盖该方法
	protected void updateAfterDeal(user record)throws Exception{
		 if(record.getAreaID()!=null)
		update(record);
		}
	private int update(user record)throws Exception{
		//String hql="update car car,area area, user user  set car.areaName=area.name,car.adderName=user.name  where car.id=:pojoID and car.areaID=area.id and car.adderID=user.id";
		String hql="update user user,area area set user.areaName=area.name where user.id=:pojoID and user.areaID=area.id";
		SQLQuery query=getSession().createSQLQuery(hql);
		query.setInteger("pojoID",record.getId());
		return query.executeUpdate();
	}
	public int updates(List<user> pojos)throws Exception{
		List<Integer> ids=new ArrayList<Integer>(0);
		 for(user u:pojos)
			 ids.add(u.getId());
		String hql="update user user,area area set user.areaName=area.name where user.areaID=area.id and user.id in(:ids)";
		SQLQuery query=getSession().createSQLQuery(hql);
		query.setParameterList("ids", ids);
		return query.executeUpdate();
	}
}
