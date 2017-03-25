package servicesDao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.houseMapper;
import dao.residentMapper;
import dao.query.houseQueryParams;
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
				 residentMapper.insertSelective(re);
			 }
		 }
		 return size;
	}

	
	public int updateNoNull(house record) throws Exception {
		   residentMapper.deleteResidentHouseInfo(record);
		int size= super.updateNoNull(record);
		if(ListUtil.isNotEmpty(record.getResidents())){
			 for(resident re:record.getResidents()){
				 re.setHouseID(record.getId());
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
