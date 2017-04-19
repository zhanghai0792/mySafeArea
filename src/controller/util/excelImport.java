package controller.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;



import controller.userLogin.currentUser;
import factory.applicationFactory;
import pojo.area;
import pojo.car;
import pojo.house;
import pojo.policeStation;
import pojo.resident;
import pojo.shop;
import pojo.user;
import servicesDao.areaServiceDao;
import servicesDao.houseServiceDao;
import util.StringUtil;

@Component
public class excelImport {
   @Autowired
	areaServiceDao areaDao;
   @Autowired
	houseServiceDao houseDao;
   
   public List readExcelToData(MultipartFile mFile,Class claz)throws Exception{
	   boolean isResident=false;
	   Map<String,Integer> areas=null;
	   Map<String,Integer> houses=null;
	   String[] dataFormate=null;
	   if(claz!=car.class&&claz!=shop.class&&claz!=house.class&&claz!=resident.class&&claz!=area.class&&claz!=policeStation.class&&claz!=user.class)
		   throw new Exception("当前没有开放此模块导入功能");
	  if(claz==car.class||claz==shop.class||claz==house.class||claz==resident.class||claz==user.class){
		  if(claz!=user.class)
	   areas=areaDao.getAreasOfPolic(currentUser.getCurrentUser().getPoliceID());
		  else
			  areas=areaDao.getAreasOfPolic(applicationFactory.fjId);  
	   if(areas==null||areas.size()==0)
		   throw new Exception("小区表没有记录，请添加小区信息");
	   if(claz==resident.class){
		   isResident=true;
		   houses=houseDao.getHouseOfPolic(currentUser.getCurrentUser().getPoliceID());
		   if(houses==null||houses.size()==0)
			   throw new Exception("房屋表没有记录，请添加房屋信息");
	   }
	   if(claz==car.class){
		   dataFormate=car.excelFormate;
	   }else if(claz==house.class){
		   dataFormate=house.excelFormate;
	   }else if(claz==shop.class){
		   dataFormate=shop.excelFormate;
	   }else if(claz==resident.class){
		   dataFormate=resident.excelFormate;
	   }else if(claz==user.class){
		   dataFormate=user.excelFormate;
	   } 
	   return analysisExcel(mFile.getInputStream(), dataFormate, areas, houses, isResident,claz);
	   }else{
		   if(claz==area.class){
			   dataFormate=area.excelFormate;
		   }else if(claz==policeStation.class){
			   dataFormate=policeStation.excelFormate;
		   }
	   
	    return analysisExcel(mFile.getInputStream(), dataFormate,claz);
	   }
   }
   public  List analysisExcel(InputStream file,String[] excelTemp,Class claz) throws Exception{
	   Workbook workbook = WorkbookFactory.create(file);// ---统一实现
	 		Sheet sheet = workbook.getSheetAt(0);// 获得第一个工作簿对象
	 		Row row = null;
	 		
	 		ArrayList list = new ArrayList(0);// 定义map对象用于保存读取到的excel文件
	 		Row rowField1 = sheet.getRow(0);
	               if(!excelTemp[excelTemp.length-1].equals(getValue(rowField1.getCell(0)))){
	            		System.out.println("导入excel的模板与数据不匹配");   
	            	   return null;
	            		   }
	               System.out.println("开始读取excel的数据");  
	 		Row rowField = sheet.getRow(2);// 获得表格字段
	 		for (int i = 2; i <= sheet.getLastRowNum(); i++) {// 循环单元格
	 			
	 			HashMap<String, String> map = new HashMap<String, String>(0);
	 			row = sheet.getRow(i);
	 			int k = 0;
	 			if (row != null) {
	 				for (int j = 0; j < rowField.getLastCellNum(); j++) {// 循环列
	 					if (rowField.getCell(j) != null) {// 此列的字段值不为空
	 						String val = getValue(row.getCell(j));
	 						if (val == null || "".equals(val))
	 							k++;
	 						map.put(excelTemp[j], val);
	 					}

	 				}
	 			}
	 			if (k != rowField.getLastCellNum()){
	 				
	 				if(claz==area.class){
	 					list.add(area.dataChange(map));
	 				}else if(claz==policeStation.class){
	 					list.add(policeStation.dataChange(map));
	 				}				
	 				}
	 		}
	 		workbook.close();
	 		file.close();
	 		return list; 
	   
	   
   }
  
   public  List analysisExcel(InputStream file,String[] excelTemp,Map<String,Integer> areas,Map<String,Integer> houses,boolean isResident,Class claz) throws Exception {
		
	   Workbook workbook = WorkbookFactory.create(file);// ---统一实现
		Sheet sheet = workbook.getSheetAt(0);// 获得第一个工作簿对象
		Row row = null;
		String areaName;
		Integer areaID;
		String houseName=null;
		ArrayList list = new ArrayList(0);// 定义map对象用于保存读取到的excel文件
		Row rowField1 = sheet.getRow(0);
              if(!excelTemp[excelTemp.length-1].equals(getValue(rowField1.getCell(0)))){
           		System.out.println("导入excel的模板与数据不匹配");   
           	   return null;
           		   }
              System.out.println("开始读取excel的数据");  
		Row rowField = sheet.getRow(2);// 获得表格字段
		for (int i = 2; i <= sheet.getLastRowNum(); i++) {// 循环单元格
			areaName=null;
			areaID=null;
			houseName=null;
			HashMap<String, String> map = new HashMap<String, String>(0);
			row = sheet.getRow(i);
			int k = 0;
			if (row != null) {
				for (int j = 0; j < rowField.getLastCellNum(); j++) {// 循环列
					if (rowField.getCell(j) != null) {// 此列的字段值不为空
						String val = getValue(row.getCell(j));
						if (val == null || "".equals(val))
							k++;
						map.put(excelTemp[j], val);
					}

				}
			}
			if (k != rowField.getLastCellNum()){
				if(claz!=user.class){
				areaName=map.get("areaName");
				if(StringUtil.isEmpty(areaName)){
					throw new Exception("存在记录没有小区名,无法导入");
				}
				areaID=areas.get(map.get("areaName"));
				if(areaID==null)
					throw new Exception(map.get("areaName")+"在本系统的小区表中不存在");
				map.put("areaID", areaID+"");
				}
				
				if(isResident){
					houseName=map.get("houseName");
					if(StringUtil.isEmpty(houseName)){
						throw new Exception("存在记录没有房屋名,无法导入");
					}
					Integer houseId=houses.get(map.get("houseName"));
					if(houseId==null)
						throw new Exception(map.get("houseName")+"在本系统的房屋表中不存在");
					map.put("houseID", houseId+"");
				}
				if(claz==car.class){
					list.add(car.dataChange(map));
				}else if(claz==shop.class){
					list.add(shop.dataChange(map));
				}else if(claz==house.class){
					list.add(house.dataChange(map));
				}else if(claz==resident.class){
					list.add(resident.dataChange(map));
				}else if(claz==user.class){
					list.add(user.dataChange(map));
				}				
				}
		}
		workbook.close();
		file.close();
		return list;
	}
   public String getValue(Cell cell) {
		String value = "";
		if (null == cell) {
			return null;
		}
		switch (cell.getCellType()) {
		// 数值型
		case Cell.CELL_TYPE_NUMERIC:
			if (HSSFDateUtil.isCellDateFormatted(cell)) {
				// 如果是date类型则 ，获取该cell的date值
				Date date = HSSFDateUtil.getJavaDate(cell.getNumericCellValue());
				value = StringUtil.DateTimeToString(date);
			} else {// 纯数字
				BigDecimal big = new BigDecimal(cell.getNumericCellValue());
				value = big.toString();
				if (null != value && !"".equals(value.trim())) {
					String[] item = value.split("[.]");
					if (1 < item.length && item[1].trim().length() > 3) {// 如果小数点后面大于三位，则保留三位小数
						value = item[0] + "." + item[1].trim().substring(0, 3);
					}
				}
			}
			break;
		// 字符串类型
		case Cell.CELL_TYPE_STRING:
			value = cell.getStringCellValue().toString();
			break;
		// 公式类型
		case Cell.CELL_TYPE_FORMULA:
			// 读公式计算值
			try {
				value = String.valueOf(cell.getNumericCellValue());
			} catch (Exception e) {
				// e.printStackTrace();
				value = cell.getStringCellValue().toString();
			}

			if (value.equals("NaN")) {// 如果获取的数据值为非法值,则转换为获取字符串
				value = cell.getStringCellValue().toString();
			}
			break;
		// 布尔类型
		case Cell.CELL_TYPE_BOOLEAN:
			value = " " + cell.getBooleanCellValue();
			break;
		// 空值
		case Cell.CELL_TYPE_BLANK:
			value = null;
			// LogUtil.getLogger().error("excel出现空值");
			break;
		// 故障
		case Cell.CELL_TYPE_ERROR:
			value = null;
			// LogUtil.getLogger().error("excel出现故障");
			break;
		default:
			value = cell.getStringCellValue().toString();
		}
		if (value != null && "null".endsWith(value.trim())) {
			value = null;
		}
		return value;
	} 
   
public areaServiceDao getAreaDao() {
	return areaDao;
}
public houseServiceDao getHouseDao() {
	return houseDao;
}
public void setAreaDao(areaServiceDao areaDao) {
	this.areaDao = areaDao;
}
public void setHouseDao(houseServiceDao houseDao) {
	this.houseDao = houseDao;
}
  
   
   
   
   
}
