package pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import controller.userLogin.currentUser;
import factory.applicationFactory;
import util.StringUtil;

public class area implements pojoModel{
public static final int photoType=1;
private Integer id;//小区ID
private String name;//小区名称
private String introduction;//简介

private Boolean isDelete;//是否已经删除
private List<String> photos=new ArrayList<String>(0);//新闻中包含的图片
private Integer policeID;
private String policeName;


public Integer getId() {
	return id;
}
public void setId(Integer id) {
	this.id = id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getIntroduction() {
	return introduction;
}
public void setIntroduction(String introduction) {
	this.introduction = introduction;
}

public Boolean getIsDelete() {
	return isDelete;
}
public void setIsDelete(Boolean isDelete) {
	this.isDelete = isDelete;
}
public List<String> getPhotos() {
	return photos;
}
public void setPhotos(List<String> photos) {
	this.photos = photos;
}
public area() {
	super();
	// TODO Auto-generated constructor stub
}
public area(area area) {
	super();
	if(area!=null){
	this.id = area.id;
	this.name = area.name;
	this.isDelete=area.isDelete;
	this.policeID=area.policeID;
	this.policeName=area.policeName;
	 this.photos=new ArrayList<String>(0);
	 this.photos.addAll(area.getPhotos());
	}
}
public area(area area,String photo) {
	super();
	if(area!=null){
	this.id = area.id;
	this.name = area.name;
	this.isDelete=area.isDelete;
	this.policeID=area.policeID;
	this.policeName=area.policeName;
	 this.photos=new ArrayList<String>(0);
	 this.photos.add(photo);
	}
}

public Integer getPoliceID() {
	return policeID;
}
public String getPoliceName() {
	if(policeID!=null)
		return applicationFactory.polices.get(policeID);
	return policeName;
}
public void setPoliceID(Integer policeID) {
	this.policeID = policeID;
}
public void setPoliceName(String policeName) {
	this.policeName = policeName;
}

public static String[] excelFormate=new String[]{"name","introduction","policeID","八里湖分局辖区小区信息导入表"};
public static area dataChange(Map<String,String> data){
	area c=null;
	 if(data!=null)
		 c=new area();
	 c.setName(data.get("name"));
	  c.setIntroduction(data.get("introduction"));
	 // System.out.println("小区的policeID"+data.get("policeID"));
		if(StringUtil.isNotEmpty(data.get("policeID")))
		{
			c.setPoliceID(Integer.parseInt(data.get("policeID")));
		}
		c.setIsDelete(false);
		
	 
	return c;
}



}
