package dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import dao.query.agreeQueryParams;
import dao.query.versionQueryParams;
import pojo.agree;
import pojo.interaction;
import pojo.version1;
@Repository
public class versionMapper extends basicDaoImpl<version1,versionQueryParams> {

	public version1 getLastVersion(){
		String hql="select max(version1.name) from pojo.version1 version1";
		String hqlString="from pojo.version1 version1 where version1.name=:name";
		Query query=getSession().createQuery(hql);
		Integer a= (Integer) query.uniqueResult();
		getSession().clear();
		query=getSession().createQuery(hqlString);
		query.setInteger("name", a);
		return (version1) query.list().get(0);	
	}
	
}
