package dao.query;

import pojo.agree;

public class agreeQueryParams extends queryParamsModel<agree> {
   private agree agree;
	private Integer interactionID;//
	private Integer agreeID;//点赞的用户
	public agree getObj() {
		// TODO Auto-generated method stub
		return agree;
	}
	public agree getAgree() {
		return agree;
	}
	public void setAgree(agree agree) {
		this.agree = agree;
	}
	
	public String getDetailQueryHQL() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public String getOrderBy() {
		return " agree.agreeTime desc";
	}
	
	public String getBasicQueryCondition() {
		return " where (:interactionID IS NULL or agree.interactionID=:interactionID) and (:agreeID IS NULL or agree.agreeID=:agreeID)";
	}
	
	public boolean getDetailsInBasic() {
		return false;
	}
	public Integer getInteractionID() {
		return interactionID;
	}
	public void setInteractionID(Integer interactionID) {
		this.interactionID = interactionID;
	}
	public Integer getAgreeID() {
		return agreeID;
	}
	public void setAgreeID(Integer agreeID) {
		this.agreeID = agreeID;
	}

}
