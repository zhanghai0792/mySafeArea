package dao.query;

import pojo.reply;

public class replyQueryParams extends queryParamsModel<reply>{

	private reply reply;
	private Integer interactionID;//互动ID
	private Integer replyerID;//评论人|回复人
	public reply getObj() {
		// TODO Auto-generated method stub
		return reply;
	}
	public reply getReply() {
		return reply;
	}
	public void setReply(reply reply) {
		this.reply = reply;
	}
	
	public String getOrderBy() {
		// TODO Auto-generated method stub
		return " reply.replyTime desc";
	}
	
	public String getBasicQueryCondition() {
		
		return " where ((:interactionID IS NULL or reply.interactionID=:interactionID) and (:replyerID IS NULL or :replyerID=reply.replyerID))";
	}
	
	public String getDetailQueryHQL() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public boolean getDetailsInBasic() {
		// TODO Auto-generated method stub
		return false;
	}
	public Integer getInteractionID() {
		return interactionID;
	}
	public void setInteractionID(Integer interactionID) {
		this.interactionID = interactionID;
	}
	public Integer getReplyerID() {
		return replyerID;
	}
	public void setReplyerID(Integer replyerID) {
		this.replyerID = replyerID;
	}

}
