package dao.query;

import java.util.List;

import controller.userLogin.currentUser;
import pojo.area;
import pojo.car;
import util.StringUtil;

public class carQueryParams extends queryParamsModel<car>{

	private car car;
	private Integer areaID;
	private String condition;
	private String condition_change;
	private Integer carID;
	
	public Integer getCarID() {
		return carID;
	}
	public void setCarID(Integer carID) {
		this.carID = carID;
	}
	public car getObj() {
		// TODO Auto-generated method stub
		return car;
	}
	public car getCar() {
		return car;
	}
	public void setCar(car car) {
		this.car = car;
	}
	public Integer getAreaID() {
		return areaID;
	}
	public void setAreaID(Integer areaID) {
		this.areaID = super.setNullId(areaID);
	}
	public String getCondition() {
		return condition;
	}
	public void setCondition(String condition) {
		if(StringUtil.isNotEmpty(condition)){
			this.condition = condition.toUpperCase();
		   this.condition_change="%"+condition+"%";	
		}
	}
	
	public String getOrderBy() {
		// TODO Auto-generated method stub
		return " car.createDate desc";
	}
	
	public String getBasicQueryCondition() {
		if(policeID==null)
		       policeID=currentUser.getCurrentUser().getPoliceID();	
		String hql=" where (:carID IS NULL or car.id=:carID) and(:areaID IS NULL or car.areaID=:areaID) and (:policeID = :fjid or :policeID=car.policeID) and (:condition_change IS NULL or car.plateNum like :condition_change or car.carNum like :condition_change or car.cardID like :condition_change or car.owner like :condition_change or car.pinYin like :condition_change)";
		return hql;
	}
	
	public String getDetailQueryHQL() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public boolean getDetailsInBasic() {
		return false;
	}
	public String getCondition_change() {
		return condition_change;
	}
	public void setCondition_change(String condition_change) {
		this.condition_change = condition_change;
	}
	

}
