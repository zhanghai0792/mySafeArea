package pojo;

import factory.applicationFactory;

public class user implements pojoModel {
	public static final int photoType = 2;
	private Integer id;// ID
	private String phone;// 手机号
	private String name;// 姓名
	private String password;// 密码
	private Integer type;// 类型0:业主，1：物业，2：民警,3:web管理员
	private Integer areaID;// 小区ID
	private Boolean isDelete;// 是否已经删除，失效
	public String areaName;// 小区名
	private String header;
	private Integer policeID;
	private String policeName;
	
	public Integer getPoliceID() {
		return policeID;
	}
	public String getPoliceName() 
	{
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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getAreaID() {
		return areaID;
	}

	public void setAreaID(Integer areaID) {
		this.areaID = areaID;
	}

	public Boolean getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Boolean isDelete) {
		this.isDelete = isDelete;
	}

	public String getHeader() {
		return header;
	}

	public void setHeader(String header) {
		this.header = header;
	}

	public static int getPhototype() {
		return photoType;
	}

	public user() {
		super();
		// TODO Auto-generated constructor stub
	}

	public user(user user,String areaName) {
		super();
		if(user!=null){
		this.id = user.id;
		this.phone = user.phone;
		this.name = user.name;
		this.password = user.password;
		this.type = user.type;
		this.areaID = user.areaID;
		this.isDelete = user.isDelete;
		this.areaName = areaName;
		this.header = user.header;}
	}

	

}
