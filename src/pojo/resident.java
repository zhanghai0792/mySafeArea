package pojo;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

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
   
	
	
	public String houseName;// 房屋名
	public String adderName;// 采集人姓名
    private Integer areaID;
    private String areaName;
    
    private Integer policeID;
	private String policeName;
	
	
	private String mark;//备注
     private String pCategory;//人口管理类别
     private String work;//所属单位

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

	

	

}
