package pojo;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import factory.applicationFactory;

public class shop implements pojoModel{
private Integer id;//主键
private String name;//名称
private String address;//地址
private String businessScope;//经营范围
private String owner;//业主姓名
private String pinYin;//业主姓名拼音
private String cardID;//身份证号
private String phone;//联系电话
private Integer areaID;//小区ID
private Integer adderID;//采集人ID
@JsonFormat(pattern="yyyy-MM-dd")
private Date createDate;//采集时间

public String areaName;//小区名
public String adderName;//采集人姓名
public Boolean isDelete;
private Integer policeID;
private String policeName;
public String photo;


private String mark;//备注


public String getMark() {
	return mark;
}
public void setMark(String mark) {
	this.mark = mark;
}

public String getPhoto() {
	return photo;
}
public void setPhoto(String photo) {
	this.photo = photo;
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


public Boolean getIsDelete() {
	return isDelete;
}
public void setIsDelete(Boolean isDelete) {
	this.isDelete = isDelete;
}
public Integer getId() {
	return id;
}
public void setId(Integer id) {
	this.id = id;
}
public String getAreaName() {
	return areaName;
}
public void setAreaName(String areaName) {
	this.areaName = areaName;
}
public String getAdderName() {
	return adderName;
}
public void setAdderName(String adderName) {
	this.adderName = adderName;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getAddress() {
	return address;
}
public void setAddress(String address) {
	this.address = address;
}
public String getBusinessScope() {
	return businessScope;
}
public void setBusinessScope(String businessScope) {
	this.businessScope = businessScope;
}
public String getOwner() {
	return owner;
}
public void setOwner(String owner) {
	this.owner = owner;
}
public String getPinYin() {
	return pinYin;
}
public void setPinYin(String pinYin) {
	this.pinYin = pinYin;
}
public String getCardID() {
	return cardID;
}
public void setCardID(String cardID) {
	this.cardID = cardID;
}
public String getPhone() {
	return phone;
}
public void setPhone(String phone) {
	this.phone = phone;
}
public Integer getAreaID() {
	return areaID;
}
public void setAreaID(Integer areaID) {
	this.areaID = areaID;
}
public Integer getAdderID() {
	return adderID;
}
public void setAdderID(Integer adderID) {
	this.adderID = adderID;
}
public Date getCreateDate() {
	return createDate;
}
public void setCreateDate(Date createDate) {
	this.createDate = createDate;
}
public shop(shop shop, String areaName, String adderName) {
	super();
	if(shop!=null){
	this.id =  shop.id;
	this.name =  shop.name;
	this.address =  shop.address;
	this.businessScope =  shop.businessScope;
	this.owner =  shop.owner;
	this.pinYin =  shop.pinYin;
	this.cardID =  shop.cardID;
	this.phone =  shop.phone;
	this.areaID =  shop.areaID;
	this.adderID =  shop.adderID;
	this.createDate =  shop.createDate;
	this.areaName = areaName;
	this.adderName = adderName;
	this.isDelete = shop.isDelete;}
}
public shop() {
	super();
	// TODO Auto-generated constructor stub
}


}
