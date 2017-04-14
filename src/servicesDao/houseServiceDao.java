package servicesDao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mchange.v1.util.StringTokenizerUtils;

import controller.userLogin.currentUser;
import dao.houseMapper;
import dao.residentMapper;
import dao.query.houseQueryParams;
import pojo.area;
import pojo.car;
import pojo.house;
import pojo.resident;
import util.ListUtil;
import util.StringUtil;
@Service
public class houseServiceDao extends serviceDaoTemplate<house, houseMapper,houseQueryParams>{
      @Autowired
      residentMapper residentMapper;
      
	
	public int save(house record) throws Exception {
		
		int size= super.save(record);
		 if(ListUtil.isNotEmpty(record.getResidents())){
			 for(resident re:record.getResidents()){
				 re.setHouseID(record.getId());
				 re.setPinYin(StringUtil.getPY(re.getName()));
				 re.setAreaID(record.getAreaID());
				 re.setPoliceID(currentUser.getCurrentUser().getPoliceID());
				 residentMapper.insertSelective(re);
			 }
		 }
		 return size;
	}

	public Map<String,Integer> getHouseOfPolic(Integer policeID){
		return dao.getHouseOfPolic(policeID);
	}
	
	
	public int updateNoNull(house record) throws Exception {
		   residentMapper.deleteResidentHouseInfo(record);
		int size= super.updateNoNull(record);
		if(ListUtil.isNotEmpty(record.getResidents())){
			 for(resident re:record.getResidents()){
				 re.setHouseID(record.getId());
				 re.setPoliceID(currentUser.getCurrentUser().getPoliceID());
				 re.setAreaID(record.getAreaID());
				 if(re.getId()==null){
				re.setPinYin(StringUtil.getPY(re.getName()));
				 residentMapper.insertSelective(re);
				 }
				 else
				 residentMapper.updateByPrimaryKeySelective(re);
			 }
		 }
		 return size;
	}

	
	public int delete(house record) throws Exception {
		if(ListUtil.isNotEmpty(record.getResidents())){
			 for(resident re:record.getResidents()){
				 re.setHouseID(null);
				 residentMapper.updateByPrimaryKeySelective(re);
			 }
		 }
		return super.delete(record);
	}
	
	public List<String> getDeletePhotos(house a)throws Exception{
		/*house aTemp=dao.load(a.getId());
	////String oldPhoto=aTemp.getPhoto();
		List<String> residents=residentMapper.getPhotos(a);
		List<String> aa=new ArrayList<String>(0);
		 if(StringUtil.isNotEmpty(oldPhoto))
		  aa.add(oldPhoto);
		 if(ListUtil.isNotEmpty(residents))
			 aa.addAll(residents);
		
		if(StringUtil.isNotEmpty(a.getPhoto()))
			aa.remove(a.getPhoto());
		
		if(ListUtil.isNotEmpty(a.residents)){
			for(resident r:a.residents){
				if(r.getId()!=null&&StringUtil.isNotEmpty(r.getHeader()))
					aa.remove(r.getHeader());
			}
		}
		 dao.evict(aTemp);
		return aa;*/
		return null;
	}
	
	public List<String> getPhotos(house a)throws Exception{
		/*house aTemp=dao.load(a.getId());
		if(StringUtil.isEmpty(aTemp.getPhoto()))
			return null;
		List<String> oldPhotos=new ArrayList<String>(0);
		oldPhotos.add(aTemp.getPhone());
		return oldPhotos;*/
		return null;
	}
	
	public int updateAll(house record) throws Exception {
		// TODO Auto-generated method stub
		return updateNoNull(record);
	}

	
	public long count(houseQueryParams map) throws Exception {
		// TODO Auto-generated method stub
		return super.count(map);
	}

	
	public house getById(Integer id) throws Exception {
		 return dao.selectByPrimaryKey(id);
	}

	
	public List<house> getBasic(houseQueryParams map) throws Exception {
		return dao.getBasic_noResident(map);
	}

	
	public List<house> getDetail(houseQueryParams map) throws Exception {
		return super.getDetail(map);
	}

	
	public houseMapper getDao() {
		// TODO Auto-generated method stub
		return super.getDao();
	}

	
	public void setDao(houseMapper dao) {
		// TODO Auto-generated method stub
		super.setDao(dao);
	}

	public residentMapper getResidentMapper() {
		return residentMapper;
	}

	public void setResidentMapper(residentMapper residentMapper) {
		this.residentMapper = residentMapper;
	}

}
