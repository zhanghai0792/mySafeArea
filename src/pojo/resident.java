package pojo;

import java.util.Date;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import controller.userLogin.currentUser;
import factory.applicationFactory;
import util.StringUtil;

public class resident implements pojoModel {
	public static final int photoType = 0;
	private Integer id;// 主键
	private String cardID;// 身份证号码 包含了性别和出生日期
	private String name;// 居住人姓名
	private String pinYin;// 居住人姓名拼音
	private String nation;// 民族
	private String adress;// 地址
	private String phone;// 手机号
	private Integer houseID;// 房屋ID
	private Integer adderID;// 采集人ID
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date createDate;// 采集时间
	private Boolean isDelete;// 是否已经删除
	private String header;// 头像
   
	private String sex;
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date birthay;
	
	
	public String houseName;// 房屋名
	public String adderName;// 采集人姓名
    private Integer areaID;
    private String areaName;
    
    private Integer policeID;
	private String policeName;
	
	
	private String mark;//备注
     private String pCategory;//人口管理类别
     private String work;//所属单位

	public String getSex() {
		return sex;
	}
	public Date getBirthay() {
		return birthay;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public void setBirthay(Date birthay) {
		this.birthay = birthay;
	}
	public String getMark() {
		return mark;
	}
	public void setMark(String mark) {
		this.mark = mark;
	}
	
	
	
	public String getpCategory() {
		return pCategory;
	}
	public String getWork() {
		return work;
	}
	public void setpCategory(String pCategory) {
		this.pCategory = pCategory;
	}
	public void setWork(String work) {
		this.work = work;
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
	
	
	
	public Integer getAreaID() {
		return areaID;
	}
	public String getAreaName() {
		return areaName;
	}
	public void setAreaID(Integer areaID) {
		this.areaID = areaID;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getHouseName() {
		return houseName;
	}

	public void setHouseName(String houseName) {
		this.houseName = houseName;
	}

	public String getAdderName() {
		return adderName;
	}

	public void setAdderName(String adderName) {
		this.adderName = adderName;
	}

	public String getCardID() {
		return cardID;
	}

	public void setCardID(String cardID) {
		this.cardID = cardID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPinYin() {
		return pinYin;
	}

	public void setPinYin(String pinYin) {
		this.pinYin = pinYin;
	}

	public String getNation() {
		return nation;
	}

	public void setNation(String nation) {
		this.nation = nation;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Integer getHouseID() {
		return houseID;
	}

	public void setHouseID(Integer houseID) {
		this.houseID = houseID;
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

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	public String getHeader() {
		return header;
	}

	public void setHeader(String header) {
		this.header = header;
	}

	public resident() {
		super();
		// TODO Auto-generated constructor stub
	}
/*
 
`cardID`,
 `name`, 
`pinYin`,
 `nation`,
 `address`,
 `phone`, 
`houseID`, 
`adderID`, 
`createDate`,
 `isDelete`, 
 `houseName`,
 `adderName`,
 `areaID`, 
`policeID`,
 `pCategory`,
 `work`,
 `mark`, 
`sex`, 
`birthay` 
 * */
	
	public static String[] excelFormate=new String[]{"name","cardID","sex","birthday","nation","address","phone","pCategory","work","houseName","areaName","八里湖分局辖区居住人口管理信息导入表"};
	public static resident dataChange(Map<String,String> data){
		resident c=null;
		 if(data!=null)
			 c=new resident();
		 c.setName(data.get("name"));
		 c.setAdress(data.get("address"));
		 c.setNation(data.get("nation"));
		c.setHouseName(data.get("houseName"));
		c.setpCategory(data.get("pCategory"));
		c.setWork(data.get("work"));
		c.setSex(data.get("sex"));
		if(StringUtil.isNotEmpty(data.get("birthday"))){
			String birthday=data.get("birthday");
			birthday.replaceAll("/", "-");
			//birthday.replaceAll("\\", "-");
			birthday.replaceAll(".", "-");
			try {
				c.setBirthay(StringUtil.StringToDateTime(birthday));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(StringUtil.isNotEmpty(data.get("houseID")))
		   c.setHouseID(Integer.parseInt(data.get("houseID")));
		
			if(StringUtil.isNotEmpty(c.getName())){
				c.setPinYin(StringUtil.getPY(c.getName()));
			}
			c.setCardID(data.get("cardID"));
			c.setPhone(data.get("phone"));
			c.setPoliceID(currentUser.getCurrentUser().getPoliceID());
			if(StringUtil.isNotEmpty(data.get("areaID")))
			c.setAreaID(Integer.parseInt(data.get("areaID")));
			c.setAreaName(data.get("areaName"));
			c.setAdderID(currentUser.getCurrentUser().getId());
			c.setAdderName(currentUser.getCurrentUser().getName());
			c.setCreateDate(new Date());
			c.setIsDelete(false);
			c.setMark(data.get("mark")); 
		 
		return c;
	}

	

}
