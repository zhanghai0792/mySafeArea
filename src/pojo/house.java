package pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonFormat;

import controller.userLogin.currentUser;
import factory.applicationFactory;
import util.StringUtil;

public class house implements pojoModel{

	private Integer id;//主键
	private Integer areaID;//小区ID
	private String address;//地址
	private Integer type;//用途（自住、出租、闲置）
	private String owner;//房主姓名
	private String pinYin;//姓名拼音
	private String ownerCardID;//房主身份证号码
	private String phone;//联系电话
	private Integer adderID;//采集人ID
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date createDate;//采集时间
	private Boolean isDelete;//是否已经删除
	
	public String areaName;//小区名
	public String adderName;//采集人姓名
	public Set<resident> residents=new HashSet<resident>(0);
	
	private Integer policeID;
	private String policeName;
	private String genre;//房屋类别
	private String ownerWorks;//屋主工作单位
	private String mark;//备注


	public String getMark() {
		return mark;
	}
	public void setMark(String mark) {
		this.mark = mark;
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
	public Integer getAreaID() {
		return areaID;
	}
	public void setAreaID(Integer areaID) {
		this.areaID = areaID;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
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
	public String getOwnerCardID() {
		return ownerCardID;
	}
	public void setOwnerCardID(String ownerCardID) {
		this.ownerCardID = ownerCardID;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
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
	public Set<resident> getResidents() {
		return residents;
	}
	public void setResidents(Set<resident> residents) {
		this.residents = residents;
	}
	
	public house() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getGenre() {
		return genre;
	}
	public String getOwnerWorks() {
		return ownerWorks;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public void setOwnerWorks(String ownerWorks) {
		this.ownerWorks = ownerWorks;
	}
/*
 
id`, 
`areaID`, 
`address`,
 `type`, 
`owner`, 
`pinYin`,
 `ownerCardID`,
 `phone`, 
`adderID`, 
`createDate`, 
`isDelete`,
 `areaName`, 
`adderName`, 
`policeID`, 
`photo`, 
`genre`, 
`ownerWorks`,
 `mark`
 
 * */
	
	public static String[] excelFormate=new String[]{"address","type","owner","ownerCardID","phone","genre","ownerWorks","areaName","mark","八里湖分局辖区居住房屋管理信息导入表"};
	public static house dataChange(Map<String,String> data){
		house c=null;
		 if(data!=null)
			 c=new house();
		 if(StringUtil.isNotEmpty(data.get("type"))){
			  if("自住".equals(data.get("type")))
				  c.setType(0);
			 }else if("租赁".equals(data.get("type"))){
				 c.setType(1);
			 }else if("闲置".equals(data.get("type"))){
				 c.setType(2);
			 }
		 c.setAddress(data.get("address"));
		 
		 c.setOwner(data.get("owner"));
			if(StringUtil.isNotEmpty(c.getAddress())){
				c.setPinYin(StringUtil.getPY(c.getAddress()));
			}
			c.setOwnerCardID(data.get("ownerCardID"));
			c.setPhone(data.get("phone"));
			c.setPoliceID(currentUser.getCurrentUser().getPoliceID());
			if(StringUtil.isNotEmpty(data.get("areaID")))
			c.setAreaID(Integer.parseInt(data.get("areaID")));
			c.setAreaName(data.get("areaName"));
			c.setAdderID(currentUser.getCurrentUser().getId());
			c.setAdderName(currentUser.getCurrentUser().getName());
			c.setCreateDate(new Date());
			c.setIsDelete(false);
			c.setGenre(data.get("genre"));
			c.setOwnerWorks(data.get("ownerWorks"));
			c.setMark(data.get("mark")); 
		 
		return c;
	}
	
	
}
