package servicesDao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.agreeMapper;
import dao.interactionMapper;
import dao.versionMapper;
import dao.query.agreeQueryParams;
import dao.query.interactionQueryParams;
import dao.query.versionQueryParams;
import pojo.agree;
import pojo.interaction;
import pojo.version1;
@Service
public class versionServiceDao extends serviceDaoTemplate<version1, versionMapper, versionQueryParams>{
	public version1 getLastVersion(){
	 return	dao.getLastVersion();
	}
}
