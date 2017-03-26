package dao;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.mchange.v2.resourcepool.ResourcePoolListener;

import dao.query.interactionQueryParams;
import pojo.interaction;
import pojo.reply;
import pojo.user;
import util.ListUtil;
@Repository
public class interactionMapper extends basicDaoImpl<interaction,interactionQueryParams>{

	public int updateAgreeNum(Integer interactionId,int step) throws Exception{
		String hql="update pojo.interaction set agreeNum=agreeNum+(:num) where id=:id";
		Query query=getSession().createQuery(hql);
		query.setInteger("num",step);
		query.setInteger("id", interactionId);
		
		return query.executeUpdate();
	}
    
	
	
    public interactionMapper() {
		super();
		hqlSelectHead="select distinct interaction from pojo.interaction interaction left join fetch interaction.photos";
		//hqlSelectHead="select new pojo.interaction( interaction) from pojo.interaction interaction left join fetch interaction.photos";
	}



    public List<interaction> getInteractionsAndIsAgree(List<interaction> pojos,user u)throws Exception{
    	String hql="select new pojo.interaction(interaction,agree) from pojo.interaction interaction left join interaction.agrees agree with interaction in(:pojos) and agree.agreeID=:userId";
    	Query query=getSession().createQuery(hql);
    	query.setParameterList("pojos",pojos);
    	query.setInteger("userId",u.getId());
    	return query.list();
    }
    
	public List<interaction> getBasic_noReply(interactionQueryParams map) throws Exception {	
		List<interaction> is=super.getBasic(map);
		Set<reply> rs=new HashSet<reply>(0);
		if(ListUtil.isNotEmpty(is)){
			interaction inter=null;
			Iterator<interaction> iter=is.iterator();
			while(iter.hasNext()){
				inter=iter.next();
				inter.setReplies(rs);
			}
		}
		return is;
	}



	public long count(interactionQueryParams map)throws Exception{
    	String hql="select count(distinct interaction.id) from pojo.interaction interaction ";
    	if (map.getBasicQueryCondition() != null) {
			hql = hql + " " + map.getBasicQueryCondition()+" and "+claz.getSimpleName()+".isDelete!=true";
		}else{
			hql=hql+" where "+claz.getSimpleName()+".isDelete!=true";
		}
    	Query query=getSession().createQuery(hql);
    	query.setProperties(map);
    	return (Long)query.uniqueResult();
    	
    }
	
	
	
}
