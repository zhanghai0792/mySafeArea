package servicesDao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.agreeMapper;
import dao.interactionMapper;
import dao.query.agreeQueryParams;
import dao.query.interactionQueryParams;
import pojo.agree;
@Service
public class agreeServiceDao extends serviceDaoTemplate<agree, agreeMapper, agreeQueryParams>{
    @Autowired
	 interactionMapper interactionMapper;
	  

	public int save(agree record) throws Exception {
		//把关注的点赞数+1
		
		interactionMapper.updateAgreeNum(record.getInteractionID(),1);
		return super.save(record);
	}


	public int delete(agree record) throws Exception {
		interactionMapper.updateAgreeNum(record.getInteractionID(),-1);
		
		return dao.deleteObject(record);
	}

	public interactionMapper getInteractionMapper() {
		return interactionMapper;
	}

	public void setInteractionMapper(interactionMapper interactionMapper) {
		this.interactionMapper = interactionMapper;
	}
	public Map<Integer,Long> currentIsAgreeInteraction(List<Integer> interactionIds,Integer userID)throws Exception{
		return dao.currentIsAgreeInteraction(interactionIds, userID);
	}  
}
