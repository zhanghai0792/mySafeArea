package servicesDao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.agreeMapper;
import dao.interactionMapper;
import dao.replyMapper;
import dao.query.interactionQueryParams;

import pojo.area;
import pojo.interaction;
@Service
public class interactionServiceDao extends serviceDaoTemplate<interaction, interactionMapper,interactionQueryParams>{
    @Autowired
	replyMapper replyMapper;
	@Autowired
    agreeMapper agreeMapper;
	
	public int save(interaction record) throws Exception {
		int count= super.save(record);
	   //super.savePhoto(record.getPhotos(), record,record.photoType);
		return count;
	}

	
	public int updateNoNull(interaction record) throws Exception {
		int count=super.updateNoNull(record);
		 //super.savePhoto(record.getPhotos(), record,record.photoType);
		return count;
	}

	
	public int delete(interaction record) throws Exception {
		/*photoQueryParams photoQuery=new photoQueryParams();
		photoQuery.setFk(record.getId());
		photoQuery.setType(area.photoType);
		//清除图片信息
		photoMapper.deletesPhotosByFK(record, area.photoType);*/
		record.setPhotos(null);
		//清除回复信息
		replyMapper.deleteByInteractionId(record.getId());
		//清除点赞的记录
		agreeMapper.deleteAgreesByInteractionId(record.getId());
		return super.delete(record);
	}

	
	public int updateAll(interaction record) throws Exception {
		// super.savePhoto(record.getPhotos(), record,record.photoType);
		return super.updateAll(record);
	}

	
	
	public long count(interactionQueryParams map) throws Exception {
		// TODO Auto-generated method stub
		return super.count(map);
	}

	
	public interaction getById(Integer id) throws Exception {
		//interactionQueryParams
		return super.getById(id);
	}

	
	public List<interaction> getBasic(interactionQueryParams map) throws Exception {
		// TODO Auto-generated method stub
		return dao.getBasic_noReply(map);
	}

	
	public List<interaction> getDetail(interactionQueryParams map) throws Exception {
		// TODO Auto-generated method stub
		return super.getDetail(map);
	}

	
	public interactionMapper getDao() {
		// TODO Auto-generated method stub
		return super.getDao();
	}

	
	public void setDao(interactionMapper dao) {
		// TODO Auto-generated method stub
		super.setDao(dao);
	}

	public replyMapper getReplyMapper() {
		return replyMapper;
	}

	public void setReplyMapper(replyMapper replyMapper) {
		this.replyMapper = replyMapper;
	}

	
	
}
