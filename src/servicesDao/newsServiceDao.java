package servicesDao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import dao.newsMapper;
import dao.query.newsQueryParams;

import pojo.area;
import pojo.news;
@Service
public class newsServiceDao extends serviceDaoTemplate<news, newsMapper,newsQueryParams>{

	
	public int save(news record) throws Exception {
		int count=super.save(record);
		//super.savePhoto(record.getPhotos(), record,record.photoType);
		return count;
	}

	
	public int updateNoNull(news record) throws Exception {
		int count=super.updateNoNull(record);
		 //super.savePhoto(record.getPhotos(), record,record.photoType);
		return count;
	}

	
	public int delete(news record) throws Exception {
		record.setPhotos(null);
		return super.delete(record);
	}

	
	public int updateAll(news record) throws Exception {
		int count=super.updateAll(record);
		 //super.savePhoto(record.getPhotos(), record,record.photoType);
		return count;
	}

	
	public long count(newsQueryParams map) throws Exception {
		// TODO Auto-generated method stub
		return super.count(map);
	}

	
	public news getById(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return super.getById(id);
	}

	
	public List<news> getBasic(newsQueryParams map) throws Exception {
		// TODO Auto-generated method stub
		return super.getBasic(map);
	}

	
	public List<news> getDetail(newsQueryParams map) throws Exception {
		// TODO Auto-generated method stub
		return super.getDetail(map);
	}

	
	public newsMapper getDao() {
		// TODO Auto-generated method stub
		return super.getDao();
	}

	
	public void setDao(newsMapper dao) {
		// TODO Auto-generated method stub
		super.setDao(dao);
	}

}
