package servicesDao;


import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.areaMapper;

import dao.query.areaQueryParams;

import pojo.area;
import pojo.news;
import pojo.pojoModel;
import util.ListUtil;

@Service
public class areaServiceDao extends serviceDaoTemplate<area, areaMapper,areaQueryParams> {
  
	
	
	public int savePhtosInWeb(area record) throws Exception {
		int count = super.save(record);
		//先保存pojo基本信息
		//有图片用当前pojo的id存储图片，再更新pojo
		//super.savePhoto(record.getPhotos(), record, record.photoType);
		return count;
	}
	public int save(area record) throws Exception {
		int count = super.save(record);
       // super.savePhoto(record.getPhotos(), record, record.photoType);
		return count;
	}
	
	
	public int updateNoNull(area record) throws Exception {
		int count = super.updateNoNull(record);
		//super.savePhoto(record.getPhotos(), record, record.photoType);
		return count;
	}

	
	public int delete(area record) throws Exception {
		record.setPhotos(null);
		return super.delete(record);//进到父类该方法上传area对象
	}

	
	public int updateAll(area record) throws Exception {
		int count = super.updateAll(record);
		//super.savePhoto(record.getPhotos(), record, record.photoType);
		return count;
	}

	public List<String> getDeletePhotos(area a)throws Exception{
		area aTemp=dao.load(a.getId());
		List<String> oldPhotos=aTemp.getPhotos();
		if(ListUtil.isEmpty(oldPhotos))
			return null;
		
		if(ListUtil.isNotEmpty(a.getPhotos())){
			oldPhotos.removeAll(a.getPhotos());
		}
	
		return oldPhotos;
	}
	
	public List<String> getPhotos(area a)throws Exception{
		area aTemp=dao.load(a.getId());
		List<String> oldPhotos=aTemp.getPhotos();
		return oldPhotos;
	}
}
