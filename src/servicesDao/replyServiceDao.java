package servicesDao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import dao.replyMapper;
import dao.query.replyQueryParams;
import pojo.reply;
@Service
public class replyServiceDao extends serviceDaoTemplate<reply, replyMapper,replyQueryParams>{

}
