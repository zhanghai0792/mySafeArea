package dao;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import dao.query.newsQueryParams;
import pojo.house;
import pojo.news;
@Repository
public class newsMapper extends basicDaoImpl<news, newsQueryParams>{

	
	public news selectByPrimaryKey(Integer id) throws Exception {
		String hql="select distinct news from news news left join fetch news.photos wherre news.id=:id";
		Query query=getSession().createQuery(hql);
		query.setInteger("id",id);
			return (news)query.uniqueResult();
	}

}
