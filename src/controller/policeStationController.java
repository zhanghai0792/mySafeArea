package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import dao.query.policeStationQueryParams;
import json.jsonResult;
import pojo.policeStation;
import servicesDao.policeStationServiceDao;



@Controller
@RequestMapping("/PoliceServlet")
public class policeStationController extends controllerTemplate<policeStation, policeStationServiceDao, policeStationQueryParams>{

	@Override
	protected jsonResult query(policeStationQueryParams im) throws Exception {
		// TODO Auto-generated method stub
		return serviceDao.getPagesResultBasic(im);
	}

}
