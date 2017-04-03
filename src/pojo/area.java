package pojo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
	}
}
public Integer getPoliceID() {
	return policeID;
}
public String getPoliceName() {
	return policeName;
}
public void setPoliceID(Integer policeID) {
	this.policeID = policeID;
}
public void setPoliceName(String policeName) {
	this.policeName = policeName;
}




}
