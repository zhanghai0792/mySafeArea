package dao.query;

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
		return null;
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
