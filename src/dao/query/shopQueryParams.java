package dao.query;

import controller.userLogin.currentUser;
import pojo.shop;
import util.StringUtil;

public class shopQueryParams extends queryParamsModel<shop>{
   private shop shop;
   private Integer areaID;
   private String condition;
   private String condition_change;
	private Integer shopID;
	
	public Integer getShopID() {
		return shopID;
	}
	public void setShopID(Integer shopID) {
		this.shopID = shopID;
	}
	public shop getObj() {
		// TODO Auto-generated method stub
		return shop;
	}
	public shop getShop() {
		return shop;
	}
	public void setShop(shop shop) {
		this.shop = shop;
	}
	public Integer getAreaID() {
		return areaID;
	}
	public void setAreaID(Integer areaID) {
		this.areaID = areaID;
	}
	public String getCondition() {
		return condition;
	}
	public void setCondition(String condition) {
		if(StringUtil.isNotEmpty(condition)){
		this.condition = condition.toUpperCase();
		 this.condition_change="%"+this.condition+"%";
		}
	}
	
	public String getOrderBy() {
		// TODO Auto-generated method stub
		return " shop.createDate desc";
	}
	
	public String getBasicQueryCondition() {
		if(policeID==null)
			 policeID=currentUser.getCurrentUser().getPoliceID();
		return " where (:shopID IS NULL or shop.id=:shopID) and (:areaID IS NULL or shop.areaID=:areaID) and (:policeID = :fjid or :policeID=shop.policeID) and (:condition_change IS NULL or shop.name like :condition_change or shop.address like :condition_change or shop.owner like :condition_change or shop.businessScope like :condition_change or shop.pinYin like :condition_change)";
	}
	
	public String getDetailQueryHQL() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public boolean getDetailsInBasic() {
		// TODO Auto-generated method stub
		return false;
	}
	public String getCondition_change() {
		return condition_change;
	}
	public void setCondition_change(String condition_change) {
		this.condition_change = condition_change;
	}

}
