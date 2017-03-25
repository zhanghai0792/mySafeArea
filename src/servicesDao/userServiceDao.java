package servicesDao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import dao.userMapper;

import dao.query.userQueryParams;
import pojo.area;
import pojo.user;
import util.ListUtil;
import util.StringUtil;
@Service
public class userServiceDao extends serviceDaoTemplate<user, userMapper, userQueryParams> {

	
	public int save(user record) throws Exception {
		int count = super.save(record);
		/*if (record.getHeader() != null)
			super.savePhoto(record.getHeader(), record, record.photoType);*/
		return count;
	}
	public boolean isRegister(String photo)throws Exception{
		return dao.isRegister(photo);
	}
	
	public user login(String phone,String pwd) throws Exception{
     	return	dao.login(phone, pwd);
	}
	public user login(userQueryParams query) throws Exception{
     	 if(query!=null&&query.getUser()!=null&&StringUtil.isNotEmpty(query.getUser().getPhone())&&StringUtil.isNotEmpty(query.getUser().getPassword())){
     		return	dao.login(query.getUser().getPhone(), query.getUser().getPassword());
     	 }else{
     		 throw new Exception("用户输入有误！");
     	 }
		
	}
	
	
	
	public int updateNoNull(user record) throws Exception {
		// TODO Auto-generated method stub
		int count = super.updateNoNull(record);
/*		if (record.getHeader() != null)
			super.savePhoto(record.getHeader(), record, record.photoType);*/
		return count;
	}

	
	public int delete(user record) throws Exception {
		int count = super.delete(record);
		/*if (record.getHeader() != null) {
			photoMapper.deleteObject(record.getHeader());
		}*/
		record.setHeader(null);
		return count;
	}

	
	public int updateAll(user record) throws Exception {

		int count = super.updateAll(record);
		/*if (record.getHeader() != null)
			super.savePhoto(record.getHeader(), record, record.photoType);*/
		return count;
	}

	
	public long count(userQueryParams map) throws Exception {
		// TODO Auto-generated method stub
		return super.count(map);
	}

	
	public user getById(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return super.getById(id);
	}

	
	public List<user> getBasic(userQueryParams map) throws Exception {
		// TODO Auto-generated method stub
		return super.getBasic(map);
	}

	
	public List<user> getDetail(userQueryParams map) throws Exception {
		// TODO Auto-generated method stub
		return super.getDetail(map);
	}

	
	public userMapper getDao() {
		// TODO Auto-generated method stub
		return super.getDao();
	}

	
	public void setDao(userMapper dao) {
		// TODO Auto-generated method stub
		super.setDao(dao);
	}

}
