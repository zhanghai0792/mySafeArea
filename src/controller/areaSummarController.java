package controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import dao.summaryMapper;
import dao.query.summaryQueryParams;
import json.jsonResult;
import pojo.area;
import pojo.car;
import pojo.house;
import pojo.shop;
import pojo.summaryPojo;
import servicesDao.summaryServiceDao;
import util.ListUtil;
import util.StringUtil;

@Controller
@RequestMapping("/AreaSummary")
public class areaSummarController extends controllerTemplate<summaryPojo, summaryServiceDao, summaryQueryParams>{

	
	protected jsonResult query(summaryQueryParams im) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	public jsonResult getAreaSummary(List<Integer> areaIds,Class summaryTypeClass)throws Exception{
		Date currentMonthDay=StringUtil.getMonthBeginDay(new Date());
		List<summaryPojo> sums=serviceDao.summaryMonthAndHistroy(areaIds, summaryTypeClass, currentMonthDay);
          return new jsonResult(sums);
	}
	public jsonResult getAllAreaSummary(Class summaryTypeClass)throws Exception{
		Date currentMonthDay=StringUtil.getMonthBeginDay(new Date());
		List<summaryPojo> sums=serviceDao.summaryMonthAndHistroy(null, summaryTypeClass, currentMonthDay);
          return new jsonResult(sums);
	}
	public jsonResult getAreasSummary(List<area> areas,Class summaryTypeClass) throws Exception{
		//Date currentMonthDay=StringUtil.getMonthBeginDay(new Date());
		
		if(ListUtil.isNotEmpty(areas)){
			List<Integer> areaIds=new ArrayList<Integer>(0);
			for(area a:areas)
				areaIds.add(a.getId());
			return getAreaSummary(areaIds, summaryTypeClass);
		}else{
			return getAllAreaSummary(summaryTypeClass);
		}
         
	}
	@RequestMapping("/car")
	public jsonResult carSummary()throws Exception{
		return getAllAreaSummary(car.class);
	}
	@RequestMapping("/house")
	public jsonResult houseSummary()throws Exception{
		return getAllAreaSummary(house.class);
	}
	@RequestMapping("/resident")
	public jsonResult residentSummary()throws Exception{
		return getAllAreaSummary(pojo.resident.class);
	}
	@RequestMapping("/shop")
	public jsonResult shopSummary()throws Exception{
		return getAllAreaSummary(shop.class);
	}
}
