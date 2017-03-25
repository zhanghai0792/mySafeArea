package dao;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import dao.query.replyQueryParams;
import pojo.news;
import pojo.reply;
@Repository
public class replyMapper extends basicDaoImpl<reply, replyQueryParams>{

	public int deleteByInteractionId(Integer id) throws Exception{
		String hql="delete from pojo.reply  where interactionID=:id";
		Query query=getSession().createQuery(hql);
		query.setInteger("id", id);
		return query.executeUpdate();
	}
	
	
}
