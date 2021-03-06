package servicesDao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import dao.shopMapper;
import dao.query.shopQueryParams;
import pojo.resident;
import pojo.shop;
import util.StringUtil;
@Service
public class shopServiceDao extends serviceDaoTemplate<shop,shopMapper,shopQueryParams>{
	public List<String> getDeletePhotos(shop a)throws Exception{
		shop aTemp=dao.load(a.getId());
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
	
	public List<String> getPhotos(shop a)throws Exception{
		shop aTemp=dao.load(a.getId());
		if(StringUtil.isEmpty(aTemp.getPhoto())){
			 dao.evict(aTemp);
			return null;}
		List<String> oldPhotos=new ArrayList<String>(0);
		oldPhotos.add(aTemp.getPhone());
		 dao.evict(aTemp);
		return oldPhotos;
	}
}
