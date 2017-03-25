package servicesDao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import dao.carMapper;
import dao.query.carQueryParams;
import pojo.car;

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


}
