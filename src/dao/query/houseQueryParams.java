package dao.query;

import java.util.List;

import pojo.car;
import pojo.house;
import util.StringUtil;

public class houseQueryParams extends queryParamsModel<house>{
    
	private house house;
	private String condition;
	private Integer houseType;
	private Integer houseID;
	private Integer areaID;
private	String condition_change;
	public house getObj() {
		// TODO Auto-generated method stub
		return house;
	}
	public house getHouse() {
		return house;
	}
	public void setHouse(house house) {
		this.house = house;
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
	public Integer getHouseType() {
		return houseType;
	}
	public void setHouseType(Integer houseType) {
		this.houseType = houseType;
	}
	public Integer getHouseID() {
		return houseID;
	}
	public void setHouseID(Integer houseID) {
		this.houseID = houseID;
	}
	
	public String getOrderBy() {
		// TODO Auto-generated method stub
		return " house.createDate desc";
	}
	
	public String getBasicQueryCondition() {
		return " where (:houseID IS NULL or house.id=:houseID) and (:areaID IS NULL or house.areaID=:areaID) and (:houseType IS NULL or house.type=:houseType) and ( (:condition_change IS NULL) or house.address like :condition_change or house.owner like :condition_change or house.pinYin like :condition_change or house.ownerCardID like :condition_change )";
	}
	
	public String getDetailQueryHQL() {
		return "select distinct house from pojo.house house left join fetch house.residents";
	}
	
	public boolean getDetailsInBasic() {	
		return true;
	}
	public Integer getAreaID() {
		return areaID;
	}
	public void setAreaID(Integer areaID) {
		this.areaID = areaID;
	}
	public String getCondition_change() {
		return condition_change;
	}
	public void setCondition_change(String condition_change) {
		this.condition_change = condition_change;
	}


	
}
