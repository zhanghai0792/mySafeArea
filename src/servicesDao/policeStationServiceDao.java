package servicesDao;

import java.util.List;

import org.springframework.stereotype.Service;

import dao.policeStationMapper;
import dao.query.policeStationQueryParams;
import factory.applicationFactory;
import pojo.policeStation;
@Service
public class policeStationServiceDao extends serviceDaoTemplate<policeStation, policeStationMapper,policeStationQueryParams>{
	public Integer getFJId(String fjName)throws Exception{
		return dao.getFJId(fjName);
	}
	public List<policeStation> getPolices()throws Exception{
		return dao.getPolices();
	}
	@Override
	public int save(policeStation record) throws Exception {
		// TODO Auto-generated method stub
		int size= super.save(record);
		applicationFactory.polices.put(record.getId(),record.getName());
		return size;
	}
	@Override
	public int updateNoNull(policeStation record) throws Exception {
		// TODO Auto-generated method stub
		int size= super.updateNoNull(record);
		applicationFactory.polices.put(record.getId(),record.getName());
		return size;
	}
	@Override
	public int delete(policeStation record) throws Exception {
		// TODO Auto-generated method stub
		int size= super.delete(record);
		applicationFactory.polices.remove(record.getId());
		return size;
	}
	@Override
	public int delete(Integer id) throws Exception {
		// TODO Auto-generated method stub
		int size= super.delete(id);
		applicationFactory.polices.remove(id);
		return size;
	}
	@Override
	public int updateAll(policeStation record) throws Exception {
		// TODO Auto-generated method stub
		int size= super.updateAll(record);
		applicationFactory.polices.put(record.getId(),record.getName());
		return size;
	}
	@Override
	public int updates(List<policeStation> pojos) throws Exception {
		// TODO Auto-generated method stub
		int size= super.updates(pojos);
		for(policeStation record:pojos)
			applicationFactory.polices.put(record.getId(),record.getName());
		return size;
	}
	@Override
	public int deletesObjects(List<policeStation> POJOS) throws Exception {
		// TODO Auto-generated method stub
		int size= super.deletesObjects(POJOS);
		for(policeStation record:POJOS)
			applicationFactory.polices.remove(record.getId());
		return size;
	}
	@Override
	public int deletes(List<policeStation> pojos) throws Exception {
		// TODO Auto-generated method stub
		int size=super.deletes(pojos);
		for(policeStation record:pojos)
			applicationFactory.polices.remove(record.getId());
		return size;
	}
	@Override
	public int deletesByIds(List<Integer> pojos) throws Exception {
		// TODO Auto-generated method stub
		int size= super.deletesByIds(pojos);
		for(Integer id:pojos)
			applicationFactory.polices.remove(id);
		return size;
	}
	
	
}
