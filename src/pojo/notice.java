package pojo;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import factory.applicationFactory;

public class notice implements pojoModel{
private Integer id;//主键
private String title;//标题
private String content;//内容
private Integer releaseID;//发布人ID
private Boolean headlineMarker;
private Boolean isDelete;
@JsonFormat(pattern="yyyy-MM-dd")
private Date releaseTime;//发布时间

public String releaseName;//发布人姓名
private Integer policeID;
private String policeName;

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


public Integer getId() {
	return id;
}
public void setId(Integer id) {
	this.id = id;
}
public String getReleaseName() {
	return releaseName;
}
public void setReleaseName(String releaseName) {
	this.releaseName = releaseName;
}
public String getTitle() {
	return title;
}
public void setTitle(String title) {
	this.title = title;
}
public String getContent() {
	return content;
}
public void setContent(String content) {
	this.content = content;
}
public Integer getReleaseID() {
	return releaseID;
}
public void setReleaseID(Integer releaseID) {
	this.releaseID = releaseID;
}
public Boolean getHeadlineMarker() {
	return headlineMarker;
}
public void setHeadlineMarker(Boolean headlineMarker) {
	this.headlineMarker = headlineMarker;
}
public Boolean getIsDelete() {
	return isDelete;
}
public void setIsDelete(Boolean isDelete) {
	this.isDelete = isDelete;
}
public Date getReleaseTime() {
	return releaseTime;
}
public void setReleaseTime(Date releaseTime) {
	this.releaseTime = releaseTime;
}
public notice() {
	super();
	// TODO Auto-generated constructor stub
}
public notice(notice notice, String releaseName) {
	super();
	if(notice!=null){
	this.id = notice.id;
	this.title = notice.title;
	this.content = notice.content;
	this.releaseID = notice.releaseID;
	this.headlineMarker = notice.headlineMarker;
	this.isDelete = notice.isDelete;
	this.releaseTime = notice.releaseTime;
	this.releaseName = releaseName;}
}


}
