package pojo;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import util.StringUtil;


public class car implements pojoModel{
private Integer id;//主键
private String plateNum;//车牌号
private String  color;//车颜色
private String brand;//品牌
private String carNum;//发动机号|车架号
private String owner;//车主|使用者姓名
private String pinYin;//姓名的拼音
private String cardID;//身份证号码
private String phone;//电话
private Integer areaID;//小区ID
private Integer adderID;//采集人ID
@JsonFormat(pattern="yyyy-MM-dd")
private Date createDate;//采集时间
private Boolean isDelete;//是否已经删除

private Integer policeID;
private String policeName;

public String areaName;//小区名称
public String adderName;//采集人姓名
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
public String getPlateNum() {
	return plateNum;
}
public void setPlateNum(String plateNum) {
	this.plateNum = plateNum;
}
public String getColor() {
	return color;
}
public void setColor(String color) {
	this.color = color;
}
public String getCarNum() {
	return carNum;
}
public void setCarNum(String carNum) {
	if(StringUtil.isNotEmpty(carNum)){
	this.carNum = carNum.toUpperCase();
	}
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
public Boolean getIsDelete() {
	return isDelete;
}
public void setIsDelete(Boolean isDelete) {
	this.isDelete = isDelete;
}
public String getBrand() {
	return brand;
}
public void setBrand(String brand) {
	
	this.brand = brand;
	
}
public car() {
	super();
	// TODO Auto-generated constructor stub
}
public car(car car,
		String areaName, String adderName) {
	super();
	if(car!=null){
	this.id = car.id;
	this.plateNum = car.plateNum;
	this.color = car.color;
	this.brand = car.brand;
	this.carNum = car.carNum;
	this.owner = car.owner;
	this.pinYin = car.pinYin;
	this.cardID = car.cardID;
	this.phone = car.phone;
	this.areaID = car.areaID;
	this.adderID = car.adderID;
	this.createDate = car.createDate;
	this.isDelete = car.isDelete;
	this.areaName = areaName;
	this.adderName = adderName;}
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
