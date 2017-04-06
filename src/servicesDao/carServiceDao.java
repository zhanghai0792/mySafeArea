package servicesDao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import dao.carMapper;
import dao.query.carQueryParams;
import pojo.area;
import pojo.car;
import util.ListUtil;
import util.StringUtil;

@Service
public class carServiceDao extends serviceDaoTemplate<car, carMapper,carQueryParams>{

	
	public int save(car record) throws Exception {
		// TODO Auto-generated method stub
		return super.save(record);
	}

	
	public int updateNoNull(car record) throws Exception {
		// TODO Auto-generated method stub
		return super.updateNoNull(record);
	}

	

	
	public int delete(car record) throws Exception {
		// TODO Auto-generated method stub
		return super.delete(record);
	}

	
	public int updateAll(car record) throws Exception {
		// TODO Auto-generated method stub
		return super.updateAll(record);
	}

	
	public long count(carQueryParams map) throws Exception {
		// TODO Auto-generated method stub
		return super.count(map);
	}

	
	public car getById(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return super.getById(id);
	}

	
	public List<car> getBasic(carQueryParams map) throws Exception {
		// TODO Auto-generated method stub
		return super.getBasic(map);
	}

	
	public List<car> getDetail(carQueryParams map) throws Exception {
		// TODO Auto-generated method stub
		return super.getDetail(map);
	}
	public List<String> getDeletePhotos(car a)throws Exception{
		car aTemp=dao.load(a.getId());
		String oldPhoto=aTemp.getPhoto();
		if(StringUtil.isEmpty(oldPhoto)){
			 dao.evict(aTemp);
			return null;}
		if(oldPhoto.equals(a.getPhoto())){
			 dao.evict(aTemp);
			return null;
		}
		List<String> aa=new ArrayList<String>(0);
		aa.add(oldPhoto);
		 dao.evict(aTemp);
		return aa;
	}
	
	public List<String> getPhotos(car a)throws Exception{
		car aTemp=dao.load(a.getId());
		if(StringUtil.isEmpty(aTemp.getPhoto())){
			 dao.evict(aTemp);
			return null;
			}
		List<String> oldPhotos=new ArrayList<String>(0);
		oldPhotos.add(aTemp.getPhone());
		 dao.evict(aTemp);
		return oldPhotos;
	}

}
