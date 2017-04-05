package dao.query;

import controller.userLogin.currentUser;
import pojo.notice;

public class noticeQueryParams extends queryParamsModel<notice>{
     private notice notice;
	
	public notice getObj() {
		// TODO Auto-generated method stub
		return notice;
	}
	public notice getNotice() {
		return notice;
	}
	public void setNotice(notice notice) {
		this.notice = notice;
	}
	
	public String getOrderBy() {
		return " notice.releaseTime desc";
	}
	
	public String getBasicQueryCondition() {
		if(getPage()==null){
			setPage(1);
			setPageSize(3);
		   }
		if(policeID==null)
		       policeID=currentUser.getCurrentUser().getPoliceID();
		return " where (:policeID = :fjid or :policeID=notice.policeID)";
	}
	
	public String getDetailQueryHQL() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public boolean getDetailsInBasic() {
		// TODO Auto-generated method stub
		return false;
	}

}
