package dao.query;

import java.util.List;

import controller.userLogin.currentUser;
import pojo.area;

public class areaQueryParams extends queryParamsModel<area>{
// private Integer policeID;
 private Integer areaID;
 private area area;
 

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
    if(policeID==null)
       policeID=currentUser.getCurrentUser().getPoliceID();
	return " where (:areaID IS NULL or area.id=:areaID) and (:policeID = :fjid or :policeID=area.policeID)";
}


public String getDetailQueryHQL() {
	 if(policeID==null)
	       policeID=currentUser.getCurrentUser().getPoliceID();
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
