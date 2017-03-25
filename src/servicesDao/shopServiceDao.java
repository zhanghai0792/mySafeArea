package servicesDao;

import org.springframework.stereotype.Service;

import dao.shopMapper;
import dao.query.shopQueryParams;
import pojo.shop;
@Service
public class shopServiceDao extends serviceDaoTemplate<shop,shopMapper,shopQueryParams>{

}
