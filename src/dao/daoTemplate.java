package dao;

import java.util.List;
import java.util.Map;

import dao.query.queryParamsModel;
import pojo.pojoModel;

public interface daoTemplate <T extends pojoModel,Query extends queryParamsModel>{
	int deleteByPrimaryKey(Integer id) throws Exception;
    int deleteObject(T t)throws Exception;
    int deletesById(Integer id)throws Exception;
    int deletesObjects(List<T> objs)throws Exception;
    T load(Integer id)throws Exception;
    T load(T pojo)throws Exception;
    
   // int insert(T record)throws Exception;
   
    int insertSelective(T record)throws Exception;

    T selectByPrimaryKey(Integer id)throws Exception;

    int updateByPrimaryKeySelective(T record)throws Exception;

  //   int updateByPrimaryKey(T record)throws Exception;
   
    //cond查询条件
   //pageIndex 分页
   //pageSize 每夜长度
    List<T> getBasic(Query map)throws Exception;
    List<T> getDetail(Query map)throws Exception;
    long count(Query map)throws Exception;
     int deleteObjectsByIds(List<Integer> ids) throws Exception;
     int updates(List<T> pojos)throws Exception;
     void evict(T t)throws Exception;
}
