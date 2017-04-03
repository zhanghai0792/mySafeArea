package controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;


import dao.query.areaQueryParams;

import json.jsonResult;
import pojo.area;

import pojo.pojoModel;
import servicesDao.areaServiceDao;

import util.AppConfig;
import util.ListUtil;

@Controller
@RequestMapping("/AreaServlet")
public class areaController extends controllerTemplate<area, areaServiceDao, areaQueryParams>{
   
	
	
	protected jsonResult query(areaQueryParams im) throws Exception {
		return getPagesDetail(im);
	}
	
	/*@RequestMapping("/add")
	@ResponseBody
	public jsonResult AddArea(area area) throws Exception{
		area.setIsDelete(false);
		serviceDao.save(area);
		List<photo> photos=new ArrayList<photo>(0);
		//photos=super.save(AppConfig.areaRootPath, area.getId().toString(), area.getId()+"_header", file, area.photoType);
		//存储物理图片
		if(ListUtil.isNotEmpty(files)){
		photos=super.saveDiskPhotosFiles(area.photoType, area.getId(), "areaPic", files); 
		area.setPhotos(photos);
		 serviceDao.updateNoNull(area);
		 }
		 jsonResult json=createMessageJsonResult(area, "新增1条记录");
		return json;
	}

	//追加图片的的更新方式
	@RequestMapping("/update")
	@ResponseBody
	public jsonResult updateArea(area area) throws Exception{
		
		if(ListUtil.isNotEmpty(files) && AppConfig.fileSaveInApp){
			List<photo> photos=new ArrayList<photo>(0);
		//photos=super.save(AppConfig.areaRootPath, area.getId().toString(), area.getId()+"_header", file, area.photoType);
			photos=super.saveDiskPhotosFiles(area.photoType, area.getId(), "areaPic", files); 
			area.setPhotos(photos);
		 }
		 serviceDao.updateNoNull(area);
		 jsonResult json=createMessageJsonResult(area, "更新1条记录");
		return json;
	}
	
	
	 * 如果附件中有图片，先将以前的图片删除，在用新的图片替换
	 * 
	public jsonResult updateAreaByOverridePhones(area area) throws Exception{
		
		if(ListUtil.isNotEmpty(file) && AppConfig.fileSaveInApp){
			List<photo> photos=new ArrayList<photo>(0);
			List<pojoModel> areas=new ArrayList<pojoModel>(0);
			areas.add(area);
			//删除小区对应的图片及物理文件
			super.deletePhotoByFKObj(areas, area.photoType);
			area.setPhotos(null);//
		//保存新的图片到目录中
			photos=super.saveDiskPhotosFiles(area.photoType, area.getId(), "areaPic", file); 		
		 area.setPhotos(photos);
		 }
		 serviceDao.updateNoNull(area);
		 jsonResult json=createMessageJsonResult(area, "更新1条记录");
		return json;
	}
	*/
	
	
	
/*	@RequestMapping("/deletePhotos")
	@ResponseBody
	public jsonResult deleteAdd(@RequestBody List<area> areas) throws Exception{
		jsonResult json=null;
		if(ListUtil.isEmpty(areas)){
			json=new jsonResult(false,"删除失败,没有删除的小区信息");
		}else{
			
			for(area a:areas)
				super.deleteAbsolutePathFile(AppConfig.areaRootPath+"/"+a.getId());
		    queryMap qMap=new queryMap();
		    qMap.add("fks", areas).add("type", area.photoType);
			photoQueryParams query=new photoQueryParams();
			query.setFks(areas);
			query.setType(area.photoType);
		   int size=photoServiceDao.deletes(query);
			//删除小区的图片，小区的信息保留
			List<pojoModel> deletAreas=(List)areas;
			if(AppConfig.fileSaveInApp){
			super.deletePhotoByFKObj(deletAreas,area.photoType);
			}else{
				photoQueryParams query = new photoQueryParams();
				query.setFks(deletAreas);
				query.setType(area.photoType);
				// 删除数据库中的记录
				photoServiceDao.deletes(query);
			}
			json=new jsonResult("删除"+areas.size()+"个小区照片");
		}
		
		return json;
	}
		*/
	
}
