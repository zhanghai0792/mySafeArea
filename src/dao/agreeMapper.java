package dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import dao.query.agreeQueryParams;
import pojo.agree;
import pojo.interaction;
@Repository
public class agreeMapper extends basicDaoImpl<agree,agreeQueryParams> {

	
	
	
	public int deleteByPrimaryKey(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return this.deletesById(id);
	}
	
	public int deleteObject(agree t) throws Exception {
		getSession().delete(t);
		return 1;
	}
	
	public int deletesById(Integer id) throws Exception {
		agree agree=(agree)getSession().load(pojo.agree.class, id);
		return this.deleteObject(agree);
	}
	
	public int deletesObjects(List<agree> objs) throws Exception {
		String hql="delete from pojo.agree agree where agree in(:pojos)";
		Query query=getSession().createQuery(hql);
		query.setParameterList("pojos", objs);
		return query.executeUpdate();
	}
	public int deleteAgreesByInteractionId(Integer interactionId)throws Exception{
		 String hql="delete from pojo.agree where interactionID=:ID";
		 Query query=getSession().createQuery(hql);
		 query.setInteger("id", interactionId);
		 return query.executeUpdate();
	}
	public Map<Integer,Long> currentIsAgreeInteraction(List<Integer> interactionIds,Integer userID)throws Exception{
		String hql="select agree.interactionID,count(agree.id) from pojo.agree agree where agree.interactionID in(:interactionIds) and agree.agreeID=:userID group by agree.interactionID";
	    Query query=getSession().createQuery(hql);
	    query.setParameterList("interactionIds", interactionIds);
	    query.setInteger("userID", userID);
	    List<Object[]> result=query.list();
	    Map<Integer, Long> maps=new HashMap<Integer,Long>(0);
	    for(Object[] objs:result){
	    	maps.put((Integer)objs[0],(Long)objs[1]);
	    }
	    return maps;
	}
}
