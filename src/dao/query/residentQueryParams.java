package dao.query;

import controller.userLogin.currentUser;
import pojo.resident;

public class residentQueryParams extends queryParamsModel<resident>{

	private resident resident;
	private Integer houseID;// 房屋ID
	private Integer adderID;// 采集人ID
	private Integer areaID;// 采集人ID
	public resident getObj() {
		// TODO Auto-generated method stub
		return resident;
	}
	public resident getResident() {
		return resident;
	}
	public void setResident(resident resident) {
		this.resident = resident;
	}
	
	public String getOrderBy() {
		// TODO Auto-generated method stub
		return " resident.createDate desc";
	}
	
	public String getBasicQueryCondition() {
		if(policeID==null)
		       policeID=currentUser.getCurrentUser().getPoliceID();
		return  " where (:houseID IS NULL or resident.houseID=:houseID) and (:areaID IS NULL or resident.areaID=:areaID) and (:policeID = :fjid or :policeID=resident.policeID)";
	}
	
	public String getDetailQueryHQL() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public boolean getDetailsInBasic() {
		// TODO Auto-generated method stub
		return false;
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
	public Integer getAreaID() {
		return areaID;
	}
	public void setAreaID(Integer areaID) {
		this.areaID = areaID;
	}
	

}
