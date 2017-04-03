package servicesDao;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.daoTemplate;

import dao.query.queryParamsModel;
import json.jsonResult;
import pojo.area;
import pojo.house;
import pojo.pojoModel;
import util.ListUtil;
@Service
public abstract class serviceDaoTemplate<T extends pojoModel,Dao extends daoTemplate<T,Query>,Query extends queryParamsModel<T>>{
    @Autowired
	public Dao dao;
    
    public   int save(T record) throws Exception{
    	return dao.insertSelective(record);
    }
   
    public int updateNoNull(T record) throws Exception{
    	return dao.updateByPrimaryKeySelective(record);
    }
    /*public final int savePhoto(photo p,T record,int type)throws Exception{
    	if(p.getId()==null){
			 p.setObjId(record);
			 p.setType(type);
			 return photoMapper.insert(p);
    	}
    	return 0;
    }
    */
    /*public final int savePhoto(Set<photo> photos,T record,int type)throws Exception{
    	 int sum=0;
    	if(ListUtil.isNotEmpty(photos)){
			 for(Object p:photos){ 
				sum=sum+ savePhoto((photo)p,record,type);}
			 }
    	return sum;
    }
    */
    //T必须是完整的对象，不能是通过new I(id)方式传入的对象
    public int delete(T record)throws Exception{
    	return dao.deleteByPrimaryKey(record.getId());
    }
    

    public int delete(Integer id)throws Exception{
    	T t=dao.load(id);
    	return delete(t);
    }
    
    //更新所有的属性，null
    public int updateAll(T record)throws Exception{
    	return dao.updateByPrimaryKeySelective(record);
    }
    
    
    public int updates(List<T> pojos)throws Exception{
    	return dao.updates(pojos);
    }
    
    public long count(Query query) throws Exception{
    	return dao.count(query);
    }
    public T getById(Integer id)throws Exception{
    	return dao.selectByPrimaryKey(id);
    }
    public List<T> getBasic(Query query)throws Exception{
    	return dao.getBasic(query);
    }
    public int deletesObjects(List<T> POJOS) throws Exception{
    	 return dao.deletesObjects(POJOS);
    }
    
    public List<T> getDetail(Query query)throws Exception{
    	return dao.getDetail(query);
    }
  /*  public List<T> getDetail (Query query)throws Exception{
    	return dao.getDetail(query);
    }*/
    
  //基本的分页查询
    public jsonResult getPagesResultBasic(Query query)throws Exception{
    	long total=count(query);
    	List datas=getBasic(query);
    	return new jsonResult(true, "查询基本信息成功", total, query.getPageSize(), datas);
    }
   // 详情是在basic查询的基础上再次查询主要用于1->N的分页查询
    public jsonResult getPagesResultDetail(Query query)throws Exception{
    	long total=count(query);
    	List datas=getDetail(query);
    	return new jsonResult(true, "查询详细信息成功", total, query.getPageSize(), datas);
    }
    
   public int deletes(List<T> pojos)throws Exception{
	   return dao.deletesObjects(pojos);
   }
   
   public int deletesByIds(List<Integer> pojos)throws Exception{
	   return dao.deleteObjectsByIds(pojos);
   }
   
	public Dao getDao() {
		return dao;
	}

	public void setDao(Dao dao) {
		this.dao = dao;
	}

	public List<String> getDeletePhotos(T a)throws Exception{
		return null;
	}
	public List<String> getPhotos(T a)throws Exception{
		return null;
	}
    
    
}
