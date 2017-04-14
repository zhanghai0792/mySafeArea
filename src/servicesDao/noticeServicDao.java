package servicesDao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import dao.noticeMapper;
import dao.query.noticeQueryParams;
import pojo.notice;
@Service
public class noticeServicDao extends serviceDaoTemplate<notice, noticeMapper,noticeQueryParams>{

	@Override
	public int save(notice record) throws Exception {
		record.setIsDelete(false);
		return super.save(record);
	}


}
