package servicesDao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import dao.residentMapper;

import dao.query.residentQueryParams;
import pojo.area;
import pojo.house;
import pojo.resident;
import util.StringUtil;
@Service
public class residentServiceDao extends serviceDaoTemplate<resident, residentMapper, residentQueryParams> {

	
	public int save(resident record) throws Exception {
		int count = super.save(record);
		/*if (record.getHeader() != null)
			super.savePhoto(record.getHeader(), record, record.photoType);*/
		return count;
	}

	
	public int updateNoNull(resident record) throws Exception {
		// TODO Auto-generated method stub
		int count = super.updateNoNull(record);
		/*if (record.getHeader() != null)
			super.savePhoto(record.getHeader(), record, record.photoType);*/
		return count;
	}

	
	public int delete(resident record) throws Exception {
		int count = super.delete(record);
		/*if (record.getHeader() != null) {
			
			photoMapper.deleteObject(record.getHeader());
		}*/
		record.setHeader(null);
		return count;
	}

	public List<String> getDeletePhotos(resident a)throws Exception{
		resident aTemp=dao.load(a.getId());
		String oldPhoto=aTemp.getHeader();
		if(StringUtil.isEmpty(oldPhoto)){
			 dao.evict(aTemp);
			return null;}
		if(oldPhoto.equals(a.getHeader())){
			 dao.evict(aTemp);
			return null;
		}
		List<String> aa=new ArrayList<String>(0);
		aa.add(oldPhoto);
		 dao.evict(aTemp);
		return aa;
	}
	
	public List<String> getPhotos(resident a)throws Exception{
		resident aTemp=dao.load(a.getId());
		if(StringUtil.isEmpty(aTemp.getHeader())){
			 dao.evict(aTemp);
			return null;}
		List<String> oldPhotos=new ArrayList<String>(0);
		oldPhotos.add(aTemp.getPhone());
		 dao.evict(aTemp);
		return oldPhotos;
	}
	
	
	public int updateAll(resident record) throws Exception {

		int count = super.updateAll(record);
		/*if (record.getHeader() != null)
			super.savePhoto(record.getHeader(), record, record.photoType);*/
		return count;
	}

	
	public long count(residentQueryParams map) throws Exception {
		// TODO Auto-generated method stub
		return super.count(map);
	}

	
	public resident getById(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return super.getById(id);
	}

	
	public List<resident> getBasic(residentQueryParams map) throws Exception {
		// TODO Auto-generated method stub
		return super.getBasic(map);
	}

	
	public List<resident> getDetail(residentQueryParams map) throws Exception {
		// TODO Auto-generated method stub
		return super.getDetail(map);
	}

	
	public residentMapper getDao() {
		// TODO Auto-generated method stub
		return super.getDao();
	}

	
	public void setDao(residentMapper dao) {
		// TODO Auto-generated method stub
		super.setDao(dao);
	}

}
