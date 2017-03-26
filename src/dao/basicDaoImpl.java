package dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import dao.daoTemplate;
import dao.query.queryParamsModel;
import pojo.pojoModel;
import util.JsonUtil;
import util.ListUtil;

//HibernateDaoSupport中提供的session是线程安全的session
//一个线程始终使用一个session，一个session不会被多个线程共用

public abstract class basicDaoImpl<T extends pojoModel, Query extends queryParamsModel> extends HibernateDaoSupport
		implements daoTemplate<T, Query> {
	// claz用来记录当前T的.class文件
	protected Class<T> claz;
	protected String hqlSelectHead;
	protected String hqlCountHead;
	private Logger logger;

	@Autowired
	public void setSessionFactory_1(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}
	//基本查询时如果涉及到对象的关联查询，则子类中设置重置关联查询的hql语句
	//一般只用用N对一的关联,此项目无用
     public void setBasicHQLHead(){}
	
	public basicDaoImpl() {
		ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
		this.claz = (Class<T>) pt.getActualTypeArguments()[0];
		logger = Logger.getLogger(claz);
		hqlSelectHead = "select distinct " + claz.getSimpleName() + " from " + claz.getName() + " "
				+ claz.getSimpleName() + " ";
		hqlCountHead = "select count( distinct " + claz.getSimpleName() + ".id) from " + claz.getName() + " "
				+ claz.getSimpleName() + " ";
		setBasicHQLHead();
	}

	
	public int deleteByPrimaryKey(Integer id) throws Exception {
		if (id != null) {
			String hql = "update " + claz.getName() + " set isDelete=true where id=:id";
			org.hibernate.Query query = getSession().createQuery(hql);
			query.setParameter("id", id);
			return query.executeUpdate();
		} else {
			throw new Exception("没有指定删除" + claz.getName() + "对象的id");
		}
	}

	
	public int deleteObject(T t) throws Exception {

		deleteByPrimaryKey(t.getId());
		return 1;
	}

	
	public int deletesById(Integer id) throws Exception {

		return deleteByPrimaryKey(id);
	}

	
	public int deletesObjects(List<T> objs) throws Exception {
		if (ListUtil.isNotEmpty(objs)) {
			String hql = "update " + claz.getName() + " "+claz.getSimpleName()+" set "+claz.getSimpleName()+".isDelete=true where " + claz.getSimpleName() + " in (:objs)";
			org.hibernate.Query query = getSession().createQuery(hql);
			query.setParameterList("objs", objs);
			return query.executeUpdate();
		} else {
			throw new Exception("删除对象" + claz.getName() + "集合为空");
		}
	}

public int deleteObjectsByIds(List<Integer> ids) throws Exception {
		if (ListUtil.isNotEmpty(ids)) {
			String hql = "update " + claz.getName() + " "+claz.getSimpleName()+" set "+claz.getSimpleName()+".isDelete=true where " + claz.getSimpleName() + ".id in (:ids)";
			org.hibernate.Query query = getSession().createQuery(hql);
			query.setParameterList("ids", ids);
			return query.executeUpdate();
		} else {
			throw new Exception("删除对象" + claz.getName() + "集合为空");
		}
	}
	
	private int insert(T record) throws Exception {
		getSession().save(record);
		return 1;
	}
   //插入记录后是否要做特殊处理，在子类中覆盖该方法
	protected void insertAfterDeal(T record)throws Exception{
		
	}
	public int insertSelective(T record) throws Exception {

		int size=insert(record);
		insertAfterDeal(record);
		return size;
	}

	
	public T selectByPrimaryKey(Integer id) throws Exception {

		return (T) getSession().get(claz, id);
	}

	public T load(Integer id) throws Exception {
		return (T) getSession().load(claz, id);
	}

	public T load(T pojo) throws Exception {
		return (T) getSession().load(claz, pojo.getId());
	}

	 //更新记录后是否要做特殊处理，在子类中覆盖该方法
	protected void updateAfterDeal(T record)throws Exception{
			
		}
	protected void updateBeforeDeal(T record)throws Exception{
		
	}
	public int updateByPrimaryKeySelective(T record) throws Exception {
		updateBeforeDeal(record);
		int size=updateByPrimaryKey(record);
		updateAfterDeal(record);
		return size;
	}

	
	public int updates(List<T> pojos)throws Exception{
		int size=0;
		for(T t:pojos){
			size=size+updateByPrimaryKeySelective(t);
		}
		return size;
	}
	
	
	private int updateByPrimaryKey(T record) throws Exception {
		getSession().update(record);
		return 1;
	}

	public List<T> getBasic(Query map) throws Exception {

		// logger.error("getBasic" + JsonUtil.getJsonString(map));

		String hql = hqlSelectHead;
		if (map.getBasicQueryCondition() != null) {
			hql = hql + " " + map.getBasicQueryCondition();
		}
		if (hqlSelectHead.equals(hql)) {
			hql = hql + " where " + claz.getSimpleName() + ".isDelete != true ";
		} else {
			hql = hql + " and " + claz.getSimpleName() + ".isDelete != true ";
		}
		if (map.getOrderBy() != null) {
			hql = hql + " order by " + map.getOrderBy();
		}
		org.hibernate.Query query = getSession().createQuery(hql);
		if (map.getPage() != null && map.getPage() > 0) {
			query.setFirstResult((map.getPage() - 1) * map.getPageSize());
			query.setMaxResults(map.getPageSize());
			// query.setMaxResults(1);
			// System.out.println("pageSize"+map.getPageSize());
		}
		if (map.getBasicQueryCondition() != null) {
			query.setProperties(map);
		}
		return query.list();
	}

	
	public long count(Query map) throws Exception {
		String hql = hqlCountHead;
		if (map.getBasicQueryCondition() != null) {
			hql = hql + " " + map.getBasicQueryCondition()+" and "+claz.getSimpleName()+".isDelete!=true";
		}else{
			hql=hql+" where "+claz.getSimpleName()+".isDelete!=true";
		}
		 
		org.hibernate.Query query = getSession().createQuery(hql);
		if (map.getBasicQueryCondition() != null) {
			query.setProperties(map);
		}
		return (Long) query.uniqueResult();
	}

	public List<T> getDetail(Query map) throws Exception {
		String hql = map.getDetailQueryHQL();
		org.hibernate.Query query = null;
		if (map.getDetailsInBasic()) {// 要在查询的基础上集成查询
			List<T> baiscs = getBasic(map);
			// System.err.println("分页条数"+baiscs.size());
			if (baiscs != null && baiscs.size() > 0) {
				// logger.error(JsonUtil.getJsonString(baiscs));
				hql = hql + " where " + claz.getSimpleName() + " in (:pojos) ";
				if (map.getOrderBy() != null)
					hql = hql + " order by" + map.getOrderBy();
				query = getSession().createQuery(hql);
				query.setParameterList("pojos", baiscs);
				System.err.println("jiru");
				return query.list();
			}
			return new ArrayList<T>(0);
		} else {
			return getBasic(map);
		}
	}

}
