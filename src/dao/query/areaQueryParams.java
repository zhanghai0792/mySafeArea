package dao.query;

import java.util.List;

import pojo.area;

public class areaQueryParams extends queryParamsModel<area>{
 private String placeStation;
 private Integer areaID;
 private area area;
public String getPlaceStation() {
	return placeStation;
}

public void setPlaceStation(String placeStation) {
	this.placeStation = placeStation;
}


public area getObj() {
	return area;
}

public area getArea() {
	return area;
}

public void setArea(area area) {
	this.area = area;
}


public String getOrderBy() {
	// TODO Auto-generated method stub
	return " area.id";
}


public String getBasicQueryCondition() {
	// TODO Auto-generated method stub
	return " where (:areaID IS NULL or area.id=:areaID)";
}


public String getDetailQueryHQL() {
	String hql="select distinct area from pojo.area area left join fetch area.photos";
	return hql;
}
public boolean getDetailsInBasic(){
	return true;
}

public Integer getAreaID() {
	return areaID;
}

public void setAreaID(Integer areaID) {
	this.areaID = areaID;
}

 


}
