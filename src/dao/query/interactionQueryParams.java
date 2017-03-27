package dao.query;

import pojo.interaction;

public class interactionQueryParams extends queryParamsModel<interaction>{

	private interaction interaction;
	private Integer interactionID;
	String hql="select distinct interaction from pojo.interaction interaction left join fetch interaction.photos left join fetch interaction.replies";
	public interaction getObj() {
		// TODO Auto-generated method stub
		return interaction;
	}
	public interaction getInteraction() {
		return interaction;
	}
	public void setInteraction(interaction interaction) {
		this.interaction = interaction;
	}
	public Integer getInteractionID() {
		return interactionID;
	}
	public void setInteractionID(Integer interactionID) {
		this.interactionID = interactionID;
	}
	
	public String getOrderBy() {
		// TODO Auto-generated method stub
		return " interaction.releaseTime desc";
	}
	
	public String getBasicQueryCondition() {
		return " where (:interactionID IS NULL or :interactionID=interaction.id)";
	}
	
	public String getDetailQueryHQL() {	
		return hql;
	}
	
	public void setDetailQueryHQL(String hql) {
		/*String hql="select distinct interaction from pojo.interaction interaction left join fetch interaction.photos left join fetch interaction.replies";
		return hql;*/
		this.hql=hql;
	}
	
	public boolean getDetailsInBasic() {
		
		return true;
	}


}
